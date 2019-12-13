package org.darbots.trajectorypregen;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class CommonFramework extends JFrame {
	private FTCFieldPanel m_Panel;
	
	protected void __setupPanel(int width, int height) {
		this.m_Panel = new FTCFieldPanel(width, height, loadImage());
	}
	
	public FTCFieldPanel getFieldPanel() {
		return this.m_Panel;
	}
	
	protected Image loadImage() {
		try {
			BufferedImage image = ImageIO.read(new File("resources/Field_NoExterior.jpg"));
			return image;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
