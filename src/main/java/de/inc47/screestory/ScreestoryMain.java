package de.inc47.screestory;

import de.inc47.screestory.gui.main.MainWindow;
import java.util.logging.Logger;
import org.eclipse.swt.widgets.Display;

/**
 *
 * @author Christoph Küpker
 */
public class ScreestoryMain {
    
    private static final Logger LOG = Logger.getLogger(ScreestoryMain.class.getName());
    public static void main(String[] args) {
        LOG.info("Screestory started");
        LOG.info("Loading user interface");
        MainWindow window = new MainWindow(new Display());
    }
}
