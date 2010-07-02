package edu.ucsc.leeps.fire.cong.client.gui;

import edu.ucsc.leeps.fire.config.Configurable;
import edu.ucsc.leeps.fire.cong.FIRE;
import edu.ucsc.leeps.fire.cong.client.Client.PEmbed;
import edu.ucsc.leeps.fire.cong.client.StrategyChanger;
import edu.ucsc.leeps.fire.cong.config.Config;
import edu.ucsc.leeps.fire.cong.server.PayoffFunction;
import edu.ucsc.leeps.fire.cong.server.ThreeStrategyPayoffFunction;
import edu.ucsc.leeps.fire.cong.server.ThresholdPayoffFunction;
import edu.ucsc.leeps.fire.cong.server.TwoStrategyPayoffFunction;

/**
 *
 * @author swolpert
 */
public class PureStrategySelector extends Sprite implements Configurable<Config> {
    private final int BUTTON_RADIUS = 15;
    private PEmbed applet;
    private float currentPercent;
    private float[] myStrat;
    private float[] opponentStrat;
    private Marker matrixTopLeft;
    private Marker matrixTopRight;
    private Marker matrixBotLeft;
    private Marker matrixBotRight;
    private float matrixSideLength;
    private Marker matrixLabel;
    private Marker[][] cellMarkers;
    private Marker[] columnLabels;
    private RadioButtonGroup buttons;
    private PayoffFunction payoffFunction, counterpartPayoffFunction;
    private StrategyChanger strategyChanger;

    public PureStrategySelector (int x, int y, int size,
            PEmbed applet, StrategyChanger strategyChanger) {
        super(x, y, size, size);
        visible = false;
        this.applet = applet;

        myStrat = new float[] {0};
        opponentStrat = new float[] {0};

        matrixTopLeft = new Marker(this, width / 4, width / 8, true, 0);
        matrixTopRight = new Marker(this, width, width / 8, true, 0);
        matrixBotLeft = new Marker(this, width / 4, 7 * (width / 8), true, 0);
        matrixBotRight = new Marker(this, width, 7 * (width / 8), true, 0);

        matrixSideLength = matrixTopRight.origin.x - matrixTopLeft.origin.x;

        matrixLabel = new Marker(this, matrixTopLeft.origin.x + matrixSideLength / 2,
                matrixTopLeft.origin.y - (applet.textAscent() + applet.textDescent()),
                true, 0);
        matrixLabel.setLabelMode(Marker.LabelMode.Top);
        matrixLabel.setLabel("Matrix");

        this.strategyChanger = strategyChanger;

        FIRE.client.addConfigListener(this);
    }

    public void update() {
        if (visible) {
            updateLabels();
        }
    }
    
    @Override
    public void draw(PEmbed applet) {
        if (visible) {
            applet.pushMatrix();
            applet.translate(origin.x, origin.y);

            matrixLabel.draw(applet);
            for (int i = 0; i < columnLabels.length; ++i) {
                columnLabels[i].draw(applet);
            }

            applet.stroke(0);
            applet.strokeWeight(2);
            
            applet.line(matrixTopLeft.origin.x, matrixTopLeft.origin.y, matrixTopRight.origin.x, matrixTopRight.origin.y);
            applet.line(matrixTopRight.origin.x, matrixTopRight.origin.y, matrixBotRight.origin.x, matrixBotRight.origin.y);
            applet.line(matrixBotLeft.origin.x, matrixBotLeft.origin.y, matrixBotRight.origin.x, matrixBotRight.origin.y);
            applet.line(matrixTopLeft.origin.x, matrixTopLeft.origin.y, matrixBotLeft.origin.x, matrixBotLeft.origin.y);

            float midpointX = (matrixTopRight.origin.x + matrixTopLeft.origin.x) / 2f;
            float midpointY = (matrixBotLeft.origin.y + matrixTopLeft.origin.y) / 2f;
            applet.line(midpointX, matrixTopLeft.origin.y, midpointX, matrixBotLeft.origin.y);
            applet.line(matrixTopLeft.origin.x, midpointY, matrixTopRight.origin.x, midpointY);

            buttons.draw(applet);

            for (int i = 0; i < cellMarkers.length; ++i) {
                for (int j = 0; j < cellMarkers[i].length; ++j) {
                    cellMarkers[i][j].draw(applet);
                }
            }

            int selection = buttons.getSelection();
            if (selection != RadioButtonGroup.NO_BUTTON &&
                    myStrat[selection] != 1) {
                for (int i = 0; i < myStrat.length; ++i) {
                    myStrat[i] = 0;
                }
                myStrat[selection] = 1;

                float[] strategy = new float[myStrat.length];
                for(int i = 0; i < myStrat.length; ++i) {
                    strategy[i] = myStrat[i];
                }
                strategyChanger.setTargetStrategy(strategy);
                strategyChanger.setCurrentStrategy(strategy);
            }
            
            applet.popMatrix();
        }
    }

    @Override
    public void setVisible(boolean isVisible) {
        visible = isVisible;
        buttons.setVisible(isVisible);
    }

    public void setEnabled(boolean enabled) {
        buttons.setEnabled(enabled);
    }

    public float[] getMyStrategy() {
        float[] strategy = new float[myStrat.length];
        for (int i = 0; i < strategy.length; ++i) {
            strategy[i] = myStrat[i];
        }
        return strategy;

    }

    public void setCurrentPercent(float percent) {
        currentPercent = percent;
    }

    public void setMyStrategy(float[] strategy) {
        for (int i = 0; i < myStrat.length; ++i) {
            myStrat[i] = strategy[i];
        }

        for (int i = 0; i < myStrat.length; ++i) {
            if (myStrat[i] == 1) {
                buttons.setSelection(i);
                break;
            }
        }
    }

    public void setCounterpartStrategy(float[] strategy) {
        for (int i = 0; i < opponentStrat.length; ++i) {
            opponentStrat[i] = strategy[i];
        }
    }

    public void configChanged(Config config) {
        int numStrategies = 0;
        if (config.payoffFunction instanceof TwoStrategyPayoffFunction) {
            numStrategies = 2;
        } else if (config.payoffFunction instanceof ThreeStrategyPayoffFunction) {
            numStrategies = 3;
        }

        if (numStrategies != 0) {
            this.payoffFunction = config.payoffFunction;
            this.counterpartPayoffFunction = config.counterpartPayoffFunction;

            myStrat = new float[numStrategies];
            opponentStrat = new float[numStrategies];

            if (payoffFunction instanceof ThresholdPayoffFunction) {
                float threshold = ((ThresholdPayoffFunction)payoffFunction).threshold;
                matrixLabel.setLabel("Threshold: " + threshold);
                matrixLabel.setVisible(true);
            } else {
                matrixLabel.setVisible(false);
            }

            cellMarkers = new Marker[numStrategies][numStrategies];
            float interval = matrixSideLength / (numStrategies * 2f);
            int offsetX = 1;
            int offsetY = 1;
            for (int i = 0; i < numStrategies; ++i) {
                offsetX = 1;
                for (int j = 0; j < numStrategies; ++j) {
                    cellMarkers[i][j] = new Marker(this, matrixTopLeft.origin.x + (j + offsetX) * interval,
                            matrixTopLeft.origin.y + (i + offsetY) * interval, true, 0);
                    cellMarkers[i][j].setLabelMode(Marker.LabelMode.Bottom);
                    ++offsetX;
                }
                ++offsetY;
            }

            columnLabels = new Marker[numStrategies];
            offsetX = 1;
            for (int i = 0; i < numStrategies; ++i) {
                columnLabels[i] = new Marker(this, matrixBotLeft.origin.x + (i + offsetX) * interval,
                        matrixBotLeft.origin.y + applet.textAscent() + applet.textDescent(),
                        true, 0);
                columnLabels[i].setLabelMode(Marker.LabelMode.Bottom);
                ++offsetX;
            }

            buttons = new RadioButtonGroup(this, BUTTON_RADIUS, matrixTopLeft.origin.y,
                    (int)matrixSideLength, numStrategies,
                    RadioButtonGroup.Alignment.Vertical, BUTTON_RADIUS, applet);
            buttons.setLabelMode(Marker.LabelMode.Right);

            if (payoffFunction instanceof ThresholdPayoffFunction) {
                columnLabels[0].setLabel("met");
                columnLabels[1].setLabel("not met");

                buttons.setLabels(new String[] {"A", "B"});
            } else if (payoffFunction instanceof TwoStrategyPayoffFunction) {
                columnLabels[0].setLabel("a");
                columnLabels[1].setLabel("b");

                buttons.setLabels(new String[] {"A", "B"});
            } else if (payoffFunction instanceof ThreeStrategyPayoffFunction) {
                columnLabels[0].setLabel("a");
                columnLabels[1].setLabel("b");
                columnLabels[2].setLabel("c");

                buttons.setLabels(new String[] {"A", "B", "C"});
            }
            buttons.setEnabled(false);
        }
        
        if (config.mixedStrategySelection) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    private void updateLabels() {
        float[] myStrategy = new float[cellMarkers.length];
        float[] opponentStrategy = new float[cellMarkers.length];

        for (int i = 0; i < cellMarkers.length; ++i) {
            for (int j = 0; j < cellMarkers[i].length; ++j) {
                for (int k = 0; k < cellMarkers.length; ++k) {
                    myStrategy[k] = 0f;
                    opponentStrategy[k] = 0f;
                }

                myStrategy[i] = 1f;
                opponentStrategy[j] = 1f;

                float myPayoff = payoffFunction.getPayoff(currentPercent, myStrategy, opponentStrategy);
                float opponentPayoff = payoffFunction.getPayoff(currentPercent, opponentStrategy, myStrategy);

                cellMarkers[i][j].setLabel(myPayoff, opponentPayoff);
            }
        }
    }
}
