package edu.ucsc.leeps.fire.cong.server;

import edu.ucsc.leeps.fire.cong.FIRE;
import edu.ucsc.leeps.fire.cong.client.ClientInterface;
import edu.ucsc.leeps.fire.cong.config.Config;
import edu.ucsc.leeps.fire.cong.logging.TickEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jpettit
 */
public class Population implements Serializable {

    private Map<Integer, ClientInterface> members;
    private long periodStartTime;
    private Set<Tuple> tuples;
    private Map<Integer, Tuple> tupleMap;

    public Population() {
        tuples = new HashSet<Tuple>();
        tupleMap = new HashMap<Integer, Tuple>();
    }

    public void configure(Map<Integer, ClientInterface> members) {
        this.members = members;
        tuples.clear();
        tupleMap.clear();
        setupTuples();
        if (FIRE.server.getConfig().preLength == 0) {
            setInitialStrategies();
        }
    }

    public void setPeriodStartTime(long timestamp) {
        periodStartTime = timestamp;
        for (Tuple tuple : tuples) {
            tuple.evalTime = timestamp;
            tuple.update();
        }
    }

    public void strategyChanged(
            float[] newStrategy,
            float[] targetStrategy,
            Integer changed, long timestamp) {
        tupleMap.get(changed).update(changed, newStrategy, targetStrategy, timestamp);
    }

    public void endSubperiod(int subperiod) {
        for (Tuple tuple : tuples) {
            tuple.endSubperiod(subperiod);
        }
    }

    public void endPeriod() {
        long timestamp = System.currentTimeMillis();
        for (Tuple tuple : tuples) {
            tuple.endPeriod(timestamp);
        }
    }

    public void logTick(int subperiod, int secondsLeft) {
        // Log the tick information
        int period = FIRE.server.getConfig().period;
        float length = FIRE.server.getConfig().length;
        float percent = (float) (length * secondsLeft) / (float) length;
        for (int member : members.keySet()) {
            TickEvent tick = new TickEvent();
            tick.period = period;
            tick.subject = member;
            tick.config = FIRE.server.getConfig(member);
            tick.subperiod = subperiod;
            tick.secondsLeft = secondsLeft;
            Tuple tuple = tupleMap.get(member);
            tick.population = tuple.population;
            tick.world = tuple.world;
            tick.strategy = tuple.strategies.get(member);
            tick.target = tuple.targets.get(member);
            tick.match = tuple.match.population;
            tick.payoff = tick.config.payoffFunction.getPayoff(
                    member, percent,
                    tuple.strategies, tuple.match.strategies,
                    tick.config);
            // get summary statistics from payoff function
            tick.popStrategy = tick.config.payoffFunction.getPopStrategySummary(member, percent, tuple.strategies, tuple.match.strategies);
            tick.matchStrategy = tick.config.payoffFunction.getPopStrategySummary(member, percent, tuple.strategies, tuple.match.strategies);
            FIRE.server.commit(tick);
        }
    }

    private class Tuple {

        public int population;
        public int world;
        public Set<Integer> members;
        public long evalTime;
        public Map<Integer, float[]> strategies;
        public Map<Integer, float[]> targets;
        public Tuple match;

        public Tuple() {
            this(tuples.size());
        }

        public Tuple(int population) {
            this.population = population;
            tuples.add(this);
            members = new HashSet<Integer>();
            strategies = new HashMap<Integer, float[]>();
            targets = new HashMap<Integer, float[]>();
        }

        public void update(int changed, float[] strategy, float[] target, long timestamp) {
            if (FIRE.server.getConfig().subperiods == 0) {
                evaluate(timestamp);
                match.evaluate(timestamp);
            }
            strategies.put(changed, strategy);
            targets.put(changed, target);
            if (FIRE.server.getConfig().subperiods == 0) {
                update();
            }
        }

        public void update() {
            for (int member : members) {
                Population.this.members.get(member).setStrategies(strategies);
            }
            for (int member : match.members) {
                Population.this.members.get(member).setMatchStrategies(strategies);
            }
        }

        public void evaluate(long timestamp) {
            float percent = (timestamp - periodStartTime) / (FIRE.server.getConfig().length * 1000f);
            float percentElapsed = (timestamp - evalTime) / (FIRE.server.getConfig().length * 1000f);
            evaluate(percent, percentElapsed);
            evalTime = timestamp;
        }

        public void evaluate(float percent, float percentElapsed) {
            for (int member : members) {
                Config config = FIRE.server.getConfig(member);
                PayoffFunction u = config.payoffFunction;
                float payoff = u.getPayoff(
                        member, percent,
                        strategies, match.strategies,
                        config);
                payoff *= percentElapsed;
                FIRE.server.addToPeriodPoints(member, payoff);
            }
        }

        public void endSubperiod(int subperiod) {
            float percentElapsed = 1f / FIRE.server.getConfig().subperiods;
            float percent = subperiod * percentElapsed;
            evaluate(percent, percentElapsed);
            match.evaluate(percent, percentElapsed);
            for (int member : members) {
                Population.this.members.get(member).endSubperiod(
                        subperiod, strategies, match.strategies);
            }
            update();

        }

        public void endPeriod(long timestamp) {
            evaluate(timestamp);
            match.evaluate(timestamp);
        }
    }

    public void setupTuples() {
        if (FIRE.server.getConfig().numTuples == 1
                || (members.size() / FIRE.server.getConfig().tupleSize) == 1) {
            setupSinglePopTuples();
        } else {
            if (FIRE.server.getConfig().assignedTuples) {
                setupAssignedTuples();
            } else {
                setupRandomTuples();
            }
        }
    }

    /*
     * Constructs a single tuple comprising all subjects
     * Tuple is linked to itself
     */
    private void setupSinglePopTuples() {
        Tuple tuple = new Tuple();
        tuple.members = members.keySet();
        tuple.match = tuple;
        for (int member : members.keySet()) {
            Config def = FIRE.server.getConfig();
            Config config = FIRE.server.getConfig(member);
            config.isCounterpart = false;
            config.payoffFunction = def.payoffFunction;
            config.counterpartPayoffFunction = def.payoffFunction;
            tupleMap.put(member, tuple);
            config.playersInTuple = members.size();
        }
    }

    private void setupAssignedTuples() {
        for (int member : members.keySet()) {
            Config config = FIRE.server.getConfig(member);
            int population = config.population;
            int match = config.match;
            Tuple p = null;
            Tuple m = null;
            for (Tuple tuple : tuples) {
                if (tuple.population == population) {
                    p = tuple;
                }
                if (tuple.population == match) {
                    m = tuple;
                }
            }
            if (p == null) {
                p = new Tuple(population);
            }
            if (m == null) {
                m = new Tuple(match);
            }
            p.members.add(member);
            p.match = m;
            tupleMap.put(member, p);
        }
        Set<Tuple> assignedMatches = new HashSet<Tuple>();
        Config def = FIRE.server.getConfig();
        for (Tuple tuple : tuples) {
            if (assignedMatches.contains(tuple)) {
                continue;
            }
            if (tuple.population > tuple.match.population) {
                tuple = tuple.match;
            }
            for (int member : tuple.members) {
                Config config = FIRE.server.getConfig(member);
                config.isCounterpart = false;
                config.payoffFunction = def.payoffFunction;
                config.counterpartPayoffFunction = def.counterpartPayoffFunction;
                config.playersInTuple = tuple.members.size();
            }
            for (int member : tuple.match.members) {
                Config config = FIRE.server.getConfig(member);
                config.isCounterpart = true;
                config.payoffFunction = def.counterpartPayoffFunction;
                config.counterpartPayoffFunction = def.payoffFunction;
                config.playersInTuple = tuple.match.members.size();
            }
            assignedMatches.add(tuple);
            assignedMatches.add(tuple.match);
        }
    }

    private void setupRandomTuples() {
        ArrayList<Integer> randomMembers = new ArrayList<Integer>();
        randomMembers.addAll(members.keySet());
        Collections.shuffle(randomMembers, FIRE.server.getRandom());
        if (FIRE.server.getConfig().tupleSize == -1) {
            FIRE.server.getConfig().tupleSize = members.size() / FIRE.server.getConfig().numTuples;
        }
        Tuple current = null;
        ArrayList<Tuple> randomTuples = new ArrayList<Tuple>();
        while (randomMembers.size() > 0) {
            if (current == null || current.members.size() == FIRE.server.getConfig().tupleSize) {
                current = new Tuple();
                randomTuples.add(current);
            }
            int member = randomMembers.remove(0);
            current.members.add(member);
            tupleMap.put(member, current);
        }
        Collections.shuffle(randomTuples, FIRE.server.getRandom());
        while (randomTuples.size() > 0) {
            Tuple tuple = randomTuples.remove(0);
            if (tuples.size() == 1) {
                tuple.match = tuple;
            } else {
                tuple.match = randomTuples.remove(0);
            }
            tuple.match.match = tuple;
            Config def = FIRE.server.getConfig();
            Tuple tuple1;
            if (FIRE.server.getRandom().nextBoolean()) {
                tuple1 = tuple;
            } else {
                tuple1 = tuple.match;
            }
            for (int member : tuple1.members) {
                Config config = FIRE.server.getConfig(member);
                config.isCounterpart = false;
                config.payoffFunction = def.payoffFunction;
                config.counterpartPayoffFunction = def.counterpartPayoffFunction;
                config.playersInTuple = tuple1.members.size();
            }
            for (int member : tuple1.match.members) {
                Config config = FIRE.server.getConfig(member);
                config.isCounterpart = true;
                config.payoffFunction = def.counterpartPayoffFunction;
                config.counterpartPayoffFunction = def.payoffFunction;
                config.playersInTuple = tuple1.match.members.size();
            }
        }
        assert (randomMembers.size() == 0);
        assert (randomTuples.size() == 0);
    }

    private void setInitialStrategies() {
        for (int client : members.keySet()) {
            float[] s;
            if (FIRE.server.getConfig().payoffFunction instanceof TwoStrategyPayoffFunction) {
                s = new float[1];
                if (FIRE.server.getConfig().mixedStrategySelection) {
                    s[0] = FIRE.server.getRandom().nextFloat();
                } else {
                    s[0] = FIRE.server.getRandom().nextBoolean() ? 1 : 0;
                }
            } else if (FIRE.server.getConfig().payoffFunction instanceof ThreeStrategyPayoffFunction) {
                s = new float[3];
                if (FIRE.server.getConfig().mixedStrategySelection) {
                    s[0] = FIRE.server.getRandom().nextFloat();
                    s[1] = (1 - s[0]) * FIRE.server.getRandom().nextFloat();
                    s[2] = 1 - s[0] - s[1];
                } else {
                    s[0] = 0;
                    s[1] = 0;
                    s[2] = 0;
                    s[FIRE.server.getRandom().nextInt(3)] = 1;
                }
            } else {
                throw new IllegalStateException("Cannot set initial strategies for given payoff function");
            }
            FIRE.server.getConfig(client).initialStrategy = s;
        }
        for (Tuple tuple : tuples) {
            for (int member : tuple.members) {
                tuple.strategies.put(member, FIRE.server.getConfig(member).initialStrategy);
                tuple.targets.put(member, FIRE.server.getConfig(member).initialStrategy);
            }
        }
        for (Tuple tuple : tuples) {
            tuple.update();
        }
    }
}
