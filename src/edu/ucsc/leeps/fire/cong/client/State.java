package edu.ucsc.leeps.fire.cong.client;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jpettit
 */
public class State {

    public int id;
    public int subperiod;
    public float currentPercent;
    public Map<Integer, float[]> strategies, matchStrategies;
    public float[] target;
    public StrategyChanger strategyChanger;

    public State(StrategyChanger changer) {
        this.strategyChanger = changer;
        currentPercent = 0;
    }

    public void setMyStrategy(float[] strategy) {
        strategies.put(id, strategy);
    }

    public float[] getMyStrategy() {
        return strategies.get(id);
    }

    public Map<Integer, float[]> getFictitiousStrategies(float[] strategy) {
        Map<Integer, float[]> fake = new HashMap<Integer, float[]>();
        for (int i : strategies.keySet()) {
            if (i == id) {
                fake.put(i, strategy);
            } else {
                fake.put(i, strategies.get(i));
            }
        }
        return fake;
    }

    public Map<Integer, float[]> getFictitiousMatchStrategies(float[] matchStrategy) {
        Map<Integer, float[]> fake = new HashMap<Integer, float[]>();
        for (int i : strategies.keySet()) {
            fake.put(i, matchStrategy);
        }
        return fake;
    }
}