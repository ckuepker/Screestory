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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

/**
 *
 * @author Christoph Küpker
 */
public class WindowsProcessFinder implements ProcessFinder {

    private final static Logger LOG = Logger.getLogger(WindowsProcessFinder.class.getName());

    private String process = null;

    @Override
    public void startListening(String processName) {
        this.process = processName;
    }

    @Override
    public boolean isListening() {
        return this.process != null;
    }

    @Override
    public void stopListening() {
        this.process = null;
    }

    @Override
    public boolean isProcessRunning() {
        try {
            String line;
            Process p = Runtime.getRuntime().exec(System.getenv("windir") 
                    + "\\system32\\" + "tasklist.exe  /fo csv /nh");
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            LOG.info("Gathering currently running processes:");
            while ((line = input.readLine()) != null) {
                String[] attributes = line.split(",");
                String processName = attributes[0].substring(1, attributes[0].length() - 1);
                LOG.info("* " + processName);
                if (processName.equals(this.process)) {
                    return true;
                }
            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
    }

}
