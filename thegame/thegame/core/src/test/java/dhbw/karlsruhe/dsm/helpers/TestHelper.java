package dhbw.karlsruhe.dsm.helpers;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.security.Permission;
import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import dhbw.karlsruhe.dsm.config.ConfigurationConstants;


public class TestHelper {
	
	public static boolean exitStatus = false;
	
	
	/**
	 * Sets the current Thread to sleep for the given duration
	 * @param ms time to sleep in ms
	 */
	public static void wait(int ms) {
		try {
			TimeUnit.MILLISECONDS.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}
	
	public static LwjglApplicationConfiguration createTestConfig() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL20 = true;
		config.width = ConfigurationConstants.SCREENWIDTH;
		config.height = ConfigurationConstants.SCREENHEIGHT;
		return config;
	}
	
	
	public static class NoExitSecurityManager extends SecurityManager {
		@Override
		public void checkPermission(Permission perm) {
			// allow anything
		}
		@Override
		public void checkPermission(Permission perm, Object context) {
			// allow anything
		}
		@Override
		public void checkExit(int status) {
			super.checkExit(status);
			throw new ExitException(status);
		}
	}

    public static class ExitException extends SecurityException 
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 5169325296866009143L;

		public ExitException(int status) 
        {
            super("There is no escape!");
            exitStatus = true;
        }
    }
    
    public static void clickOnLocation(int x, int y) {
		Gdx.app.getInput().setCursorPosition(x, y);
		try {
			Robot bot;
			bot = new Robot();
			bot.mousePress(InputEvent.BUTTON1_MASK);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
}
