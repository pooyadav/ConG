/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucsc.leeps.fire.cong.client;

/**
 *
 * @author jpettit
 */
public interface ClientInterface extends edu.ucsc.leeps.fire.client.ClientInterface {

    public float[] getStrategy();

    public void setMyStrategy(float[] s);

    public void setOpponentStrategy(float[] s);
}
