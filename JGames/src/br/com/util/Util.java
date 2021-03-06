package br.com.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

public class Util {

	public static void dialogCenter(JDialog dialog) {
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - dialog.getWidth()) / 2;
		final int y = (screenSize.height - dialog.getHeight()) / 2;
		dialog.setLocation(x, y);
	}

}
