/**
 * Copyright (c) 2012, University of California
 * All rights reserved.
 * 
 * Redistribution and use is governed by the LICENSE.txt file included with this
 * source code and available at http://leeps.ucsc.edu/cong/wiki/license
 **/

package edu.ucsc.leeps.fire.logging;

import java.io.Serializable;

/**
 *
 * @author jpettit
 */
public interface LogEvent extends Serializable {

    public String getDelimiter();
}
