package edu.ucsc.leeps.fire.cong.server;

import edu.ucsc.leeps.fire.cong.config.Config;
import java.util.Map;

/**
 *
 * @author jpettit
 */
public class QWERTYPayoffFunction extends TwoStrategyPayoffFunction {

    public float[][][] payoffs = new float[][][]{
        { //Firm A payoffs
            {0, 6, 6},
            {0, 10, 7},
            {0, 13, 12},
        },
        { //Firm B payoffs
            {0, 3, 3},
            {0, 9, 6},
            {0, 12, 11},
        }
    };

    @Override
    public float getMin() {
        return 5;
    }

    @Override
    public float getMax() {
        return 12;
    }

    @Override
    public float getPayoff(
            int id, float percent,
            Map<Integer, float[]> popStrategies, Map<Integer, float[]> matchPopStrategies,
            Config config) {
        float[] strategy = popStrategies.get(id);
        return payoffs[(int)strategy[0]][getInSame(id, strategy, matchPopStrategies)][getInSame(id, strategy, popStrategies) - 1];
    }

    public int getInSame(int id, float[] myStrategy, Map<Integer, float[]> strategies) {
        // the number of players in your own population with strategy equal to yours
        // includes yourself, so minimum is 1
        int count = 0;
        for (float[] strategy : strategies.values()) {
            boolean same = true;
            for (int i = 0; i < strategy.length; i++) {
                if (myStrategy[i] != strategy[i]) {
                    same = false;
                    break;
                }
            }
            if (same) {
                count++;
            }
        }
        return count;
    }
}
