/**
 * Copyright (c) 2012, University of California
 * All rights reserved.
 * 
 * Redistribution and use is governed by the LICENSE.txt file included with this
 * source code and available at http://leeps.ucsc.edu/cong/wiki/license
 **/

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ucsc.leeps.fire.cong.server;

/**
 *
 * @author lakersparks
 */
public class ThresholdPayoffFunction extends TwoStrategyPayoffFunction {
    public float threshold = .5f;

    /*
    @Override
    public float getPayoff(
            float percent, float[] myStrategy, float[] opponentStrategy) {
        if (thresholdMet(percent, myStrategy, opponentStrategy))
            return ((myStrategy[0] * Aa) + ((1 - myStrategy[0]) * Ba));
        else
            return ((myStrategy[0] * Ab) + ((1 - myStrategy[0]) * Bb));
    }
     * 
     */

    public boolean thresholdMet(float percent,float[] myStrategy, float[] opponentStrategy) {
        return opponentStrategy[0] >= threshold;
    }
}
