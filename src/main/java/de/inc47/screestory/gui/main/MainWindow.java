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
package de.inc47.screestory.gui.main;

import de.inc47.screestory.gui.tray.ScreestoryTrayIcon;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Main widget to display the main screen for Screestory.
 *
 * @author Christoph Küpker
 */
public class MainWindow {
    
    private final String WINDOW_TITLE = "Screestory";
    
    private Shell shell;
    private ScreestoryTrayIcon icon;

    public MainWindow(Display display) {
        init(display);
    }
    
    private void init(Display display) {
        shell = new Shell(display);
        shell.setText(WINDOW_TITLE);
        shell.setSize(250, 200);

        shell.open();
        
        icon = new ScreestoryTrayIcon(shell);
        shell.addShellListener(new ShellListener() {

            @Override
            public void shellActivated(ShellEvent e) {}

            @Override
            public void shellClosed(ShellEvent e) {}

            @Override
            public void shellDeactivated(ShellEvent e) {}

            @Override
            public void shellDeiconified(ShellEvent e) {}

            @Override
            public void shellIconified(ShellEvent e) {
                shell.setVisible(false);
                icon.enable();
            }
            
        });

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

}
