package org.darbots.trajectorypregen;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PointSpecifierFrame extends CommonFramework {
	public class PointSpecifierMouseListener implements MouseListener{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			Point clickPoint = e.getPoint();
			double[] fieldPoint = getFieldPanel().transferToFieldPoint(clickPoint.x, clickPoint.y);
			System.out.println("(" + fieldPoint[0] + "," + fieldPoint[1] + ")");
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public PointSpecifierFrame(int Width, int Height) {
		this.__setupPanel(Width, Height);
		this.setSize(Width + 15,Height + 40);
		this.getFieldPanel().addMouseListener(new PointSpecifierMouseListener());
		this.setLayout(null);
		this.add(this.getFieldPanel());
	}
}
