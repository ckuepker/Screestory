/*
 * Copyright (C) 2015 Christoph Küpker
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package de.inc47.screestory.process;

/**
 * Checks the processes running on the local system and provides information
 * whether a specific process to which this finder has been set to, is actually 
 * running.
 * @author Christoph Küpker
 */
public interface ProcessFinder {
    
    /**
     * Sets the process this finder should listen to
     * @param processName Name of the process, e.g. "javaw"
     */
    public void startListening(String processName);
    
    /**
     * Whether this finder is currently listening for any specific process
     * @return True iff this finder currently has a process to be observed
     */
    public boolean isListening();
    
    /**
     * Disables this finder to observe a set process
     */
    public void stopListening();
    
    /**
     * Checks whether the process observed by this finder is currently running
     * in at least one instance.
     * @return true iff the observed process is currently running
     */
    public boolean isProcessRunning();
}
