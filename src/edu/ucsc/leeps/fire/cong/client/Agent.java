package edu.ucsc.leeps.fire.cong.client;

import edu.ucsc.leeps.fire.cong.FIRE;
import edu.ucsc.leeps.fire.cong.config.Config;
import edu.ucsc.leeps.fire.cong.server.PayoffFunction;
import edu.ucsc.leeps.fire.cong.server.PricingPayoffFunction;
import edu.ucsc.leeps.fire.cong.server.ThreeStrategyPayoffFunction;
import edu.ucsc.leeps.fire.cong.server.TwoStrategyPayoffFunction;

/**
 *
 * @author jpettit
 */
public class Agent extends Thread {

    public volatile boolean running;
    public volatile boolean paused;

    public Agent() {
    }

    @Override
    public void run() {
        running = true;
        paused = true;
        while (running) {
            if (!paused && FIRE.client.isRunningPeriod() && !FIRE.client.isPaused()
                    && Client.state != null && Client.state.target != null && FIRE.client.getConfig() != null) {
                act(Client.state);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void act(edu.ucsc.leeps.fire.cong.client.State state) {
        Config config = FIRE.client.getConfig();
        if (config.payoffFunction instanceof TwoStrategyPayoffFunction) {
            float maxPayoff = Float.NEGATIVE_INFINITY;
            float maxStrategy = 0;
            float[] strategy = new float[]{maxStrategy};
            for (float s = 0; s <= 1; s += 0.01) {
                strategy[0] = s;
                float payoff = PayoffFunction.Utilities.getPayoff(strategy);
                if (payoff > maxPayoff) {
                    maxPayoff = payoff;
                    maxStrategy = s;
                }
            }
            float newTarget = maxStrategy + (float) (0.1 * FIRE.client.getRandom().nextGaussian());
            if (config.payoffFunction instanceof PricingPayoffFunction) {
                float newPrice = (maxStrategy * config.payoffFunction.getMax()) - config.payoffFunction.getMin();
                if (newPrice < config.marginalCost) {
                    float marginalCostTarget = config.marginalCost / (config.payoffFunction.getMax() - config.payoffFunction.getMin());
                    newTarget = marginalCostTarget;
                }
            }
            if (newTarget >= 0 && newTarget <= 1) {
                state.target[0] = newTarget;
            }
        } else if (config.payoffFunction instanceof ThreeStrategyPayoffFunction) {
            float maxPayoff = Float.NEGATIVE_INFINITY;
            float[] maxStrategy = new float[3];
            float[] strategy = new float[3];
            for (float s0 = 0; s0 <= 1; s0 += 0.01) {
                for (float s1 = 0; s0 + s1 <= 1; s1 += 0.01) {
                    strategy[0] = s0;
                    strategy[1] = s1;
                    strategy[2] = 1 - s0 - s1;
                    float payoff = PayoffFunction.Utilities.getPayoff(strategy);
                    if (payoff > maxPayoff) {
                        maxPayoff = payoff;
                        maxStrategy[0] = strategy[0];
                        maxStrategy[1] = strategy[1];
                        maxStrategy[2] = strategy[2];
                    }
                }
            }
            float[] newTarget = new float[3];
            newTarget[0] = maxStrategy[0] + (float) (0.05 * FIRE.client.getRandom().nextGaussian());
            newTarget[1] = maxStrategy[1] + (float) (0.05 * FIRE.client.getRandom().nextGaussian());
            newTarget[2] = 1 - newTarget[0] - newTarget[1];
            if (Math.abs(1 - (newTarget[0] + newTarget[1] + newTarget[2])) < Float.MIN_NORMAL) {
                state.target[0] = newTarget[0];
                state.target[1] = newTarget[1];
                state.target[2] = newTarget[2];
            }
        }
        try {
            Thread.sleep(FIRE.client.getRandom().nextInt(1000));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
