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
package de.inc47.screestory.gui.tray;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Christoph Küpker
 */
public class ScreestoryTrayIconTest {
    
    private ScreestoryTrayIcon icon;
    
    @Before
    public void setup() {
        icon = new ScreestoryTrayIcon(new Shell());
    }
    
    @After
    public void tearDown() {
        icon.disable();
    }
    
    @Test
    public void testScreestoryTrayIcon() {
        assertFalse(checkForTrayIcon());
    }
    
    @Test
    public void testEnable() {
        icon.enable();
        assertTrue(checkForTrayIcon());
    }
    
    @Test
    public void testDisable() {
        icon.enable();
        icon.disable();
        assertFalse(checkForTrayIcon());
    }

    private boolean checkForTrayIcon() {
        SystemTray tray = SystemTray.getSystemTray();
        TrayIcon[] icons = tray.getTrayIcons();
        for (TrayIcon i : icons) {
            if (i.getToolTip().equals(ScreestoryTrayIcon.TOOLTIP_TEXT)) {
                return true;
            }
        }
        return false;
    }
}
