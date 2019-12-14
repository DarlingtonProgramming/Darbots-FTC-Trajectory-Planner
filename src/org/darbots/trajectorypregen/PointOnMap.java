package org.darbots.trajectorypregen;

import org.darbots.darbotsftclib.libcore.calculations.dimentional_calculation.RobotPoint2D;
import java.awt.Color;

public class PointOnMap extends RobotPoint2D {
	Color pointColor;
	
	public PointOnMap(double X, double Y, Color color) {
		super(X, Y);
		this.pointColor = color;
		// TODO Auto-generated constructor stub
	}
	
	public PointOnMap(RobotPoint2D oldPoint, Color color) {
		super(oldPoint);
		this.pointColor = color;
	}
	
	
	
	public PointOnMap(PointOnMap oldPoint) {
		super(oldPoint);
		this.pointColor = oldPoint.pointColor;
	}
}
