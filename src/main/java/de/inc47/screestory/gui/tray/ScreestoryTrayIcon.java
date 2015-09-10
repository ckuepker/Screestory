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

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Tray icon for minimized mode.
 *
 * @author Christoph Küpker
 */
public class ScreestoryTrayIcon {

    private static final Logger LOG = Logger.getLogger(ScreestoryTrayIcon.class.getName());

    public static final String TOOLTIP_TEXT = "Screestory";

    private Shell maximizedView;
    private TrayIcon icon;

    public ScreestoryTrayIcon(Shell shell) {
        // TODO Programmatically generate an image which displays the current quota usage
        maximizedView = shell;
        try {
            Image image = ImageIO.read(new File("src/main/resources/images/tray.png"));
            icon = new TrayIcon(image, TOOLTIP_TEXT);
            icon.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    disable();
                    Display.getDefault().syncExec(new Runnable() {
                        public void run() {
                            maximizedView.setMinimized(false);
                            maximizedView.setVisible(true);
                        }
                    });

                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        } catch (IOException ex) {
            LOG.severe("Cannot load tray image from file system: " + ex.getMessage());
        }

    }

    public void enable() {
        if (SystemTray.isSupported()) {
            try {
                SystemTray.getSystemTray().add(icon);
            } catch (AWTException ex) {
                LOG.severe("Cannot add system tray icon: " + ex.getMessage());
            }

        }
    }

    public void disable() {
        if (SystemTray.isSupported()) {
            SystemTray.getSystemTray().remove(icon);
        }
    }
}
