package org.darbots.trajectorypregen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import org.darbots.darbotsftclib.libcore.calculations.dimentional_calculation.RobotPoint2D;
import org.darbots.darbotsftclib.libcore.calculations.dimentional_calculation.RobotPose2D;
import org.darbots.darbotsftclib.libcore.calculations.dimentional_calculation.XYPlaneCalculations;

import javax.swing.JPanel;

public class FTCFieldPanel extends JPanel {
	public static final double CONST_FIELD_WIDTH = 365.76, CONST_FIELD_HEIGHT = 365.76;
	public static final double CONST_HALF_FIELD_WIDTH = CONST_FIELD_WIDTH / 2.0, CONST_HALF_FIELD_HEIGHT = CONST_FIELD_HEIGHT / 2.0;
	public static final Color CONST_DEFAULT_POINT_COLOR = Color.BLACK;
	public static final Color CONST_DEFAULT_ROBOT_RECT_COLOR = Color.GRAY;
	public static final Color CONST_DEFAULT_ROBOT_ARROW_COLOR = Color.BLUE;
	
	private Image m_FieldImage;
	private ArrayList<PointOnMap> m_PointsToPlot;
	private ArrayList<OnFieldPlottable> m_OnFieldPlottables;
	public RobotPose2D RobotPose;
	public double robotWidth = 0, robotLength = 0;
	
	public FTCFieldPanel(Image fieldImage) {
		this.m_FieldImage = fieldImage;
		this.setLayout(null);
		this.m_PointsToPlot = new ArrayList<PointOnMap>();
		this.m_OnFieldPlottables = new ArrayList<OnFieldPlottable>();
		this.RobotPose = null;
	}
	
	public FTCFieldPanel(FTCFieldPanel oldPanel) {
		this.setLayout(null);
		this.m_FieldImage = oldPanel.m_FieldImage;
		this.m_PointsToPlot = new ArrayList<PointOnMap>(oldPanel.m_PointsToPlot);
		this.m_OnFieldPlottables = new ArrayList<OnFieldPlottable>(oldPanel.m_OnFieldPlottables);
		this.RobotPose = oldPanel.RobotPose;
	}
	
	public ArrayList<PointOnMap> getPointsToPlot(){
		return this.m_PointsToPlot;
	}
	
	public ArrayList<OnFieldPlottable> getOnFieldPlottables(){
		return this.m_OnFieldPlottables;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Dimension size = this.getSize();
		g.drawImage(this.m_FieldImage, 0, 0, size.width, size.height, this);
		
		//Plot all points asked to plot on the map
		for(PointOnMap thisPoint : this.m_PointsToPlot) {
			double[] actualPointToPlot = transferToPanelPoint(thisPoint.X,thisPoint.Y,size);
			int xStart = (int) actualPointToPlot[0], yStart = (int) actualPointToPlot[1];
			if(thisPoint.pointColor != null) {
				g.setColor(thisPoint.pointColor);
			}else {
				g.setColor(CONST_DEFAULT_POINT_COLOR);
			}
			g.fillRect(xStart, yStart, 1, 1);
		}
		
		//Call On Field Plottable
		for(OnFieldPlottable thisPlottable : this.m_OnFieldPlottables) {
			thisPlottable.onPlot(this, g, size);
		}
		
		//Plot robot position
		if(this.RobotPose != null) {
			double halfLength = this.robotLength / 2.0, halfWidth = this.robotWidth;
			RobotPoint2D robotFrontPos = new RobotPoint2D(halfLength, 0);
			RobotPoint2D robotLFPos = new RobotPoint2D(halfLength, halfWidth);
			RobotPoint2D robotRFPos = new RobotPoint2D(halfLength, -halfWidth);
			RobotPoint2D robotLBPos = new RobotPoint2D(-halfLength, halfWidth);
			RobotPoint2D robotRBPos = new RobotPoint2D(-halfLength, -halfWidth);
			
			RobotPoint2D fieldFrontPos = XYPlaneCalculations.getAbsolutePosition(this.RobotPose, robotFrontPos);
			RobotPoint2D fieldLFPos = XYPlaneCalculations.getAbsolutePosition(this.RobotPose, robotLFPos);
			RobotPoint2D fieldRFPos = XYPlaneCalculations.getAbsolutePosition(this.RobotPose, robotRFPos);
			RobotPoint2D fieldLBPos = XYPlaneCalculations.getAbsolutePosition(this.RobotPose, robotLBPos);
			RobotPoint2D fieldRBPos = XYPlaneCalculations.getAbsolutePosition(this.RobotPose, robotRBPos);
			RobotPoint2D fieldRobotPos = this.RobotPose.toPoint2D();
			
			double[] panelFrontPos = transferToPanelPoint(fieldFrontPos.X,fieldFrontPos.Y,size);
			double[] panelLFPos = transferToPanelPoint(fieldLFPos.X,fieldLFPos.Y,size);
			double[] panelRFPos = transferToPanelPoint(fieldRFPos.X,fieldRFPos.Y,size);
			double[] panelLBPos = transferToPanelPoint(fieldLBPos.X,fieldLBPos.Y,size);
			double[] panelRBPos = transferToPanelPoint(fieldRBPos.X,fieldRBPos.Y,size);
			double[] panelRobotPos = transferToPanelPoint(fieldRobotPos.X,fieldRobotPos.Y,size);
			
			g.setColor(CONST_DEFAULT_ROBOT_RECT_COLOR);
			int[] xPolyPoints = {
				(int) Math.round(panelLFPos[0]),
				(int) Math.round(panelRFPos[0]),
				(int) Math.round(panelLBPos[0]),
				(int) Math.round(panelRBPos[0])
			};
			int[] yPolyPoints = {
					(int) Math.round(panelLFPos[1]),
					(int) Math.round(panelRFPos[1]),
					(int) Math.round(panelLBPos[1]),
					(int) Math.round(panelRBPos[1])
			};
			g.fillPolygon(xPolyPoints, yPolyPoints, 4);
			
			g.setColor(CONST_DEFAULT_ROBOT_ARROW_COLOR);
			int[] FrontPosRounded = {
					(int) Math.round(panelFrontPos[0]),
					(int) Math.round(panelFrontPos[1])
			};
			int[] RobotPosRounded = {
					(int) Math.round(panelRobotPos[0]),
					(int) Math.round(panelRobotPos[1])
			};
			g.drawLine(FrontPosRounded[0], FrontPosRounded[1], RobotPosRounded[0], RobotPosRounded[1]);
		}
	}
	
	public double[] transferToFieldPoint(double x, double y) {
		return transferToFieldPoint(x, y, this.getSize());
	}
	
	public static double[] transferToFieldPoint(double x, double y, Dimension panelSize) {
		Dimension size = panelSize;
		double panelWidth = size.getWidth(), panelHeight = size.getHeight();
		
		double scaledX = x / panelWidth * CONST_FIELD_WIDTH;
		double scaledY = y / panelHeight * CONST_FIELD_HEIGHT;
		
		double transferredX = -scaledY + CONST_HALF_FIELD_HEIGHT;
		double transferredY = -scaledX + CONST_HALF_FIELD_WIDTH;
		
		double[] result = {transferredX, transferredY};
		return result;
	}
	
	public double[] transferToPanelPoint(double fieldX, double fieldY) {
		return transferToPanelPoint(fieldX,fieldY,this.getSize());
	}
	
	public static double[] transferToPanelPoint(double fieldX, double fieldY, Dimension panelSize) {
		Dimension size = panelSize;
		double panelWidth = size.getWidth(), panelHeight = size.getHeight();
		
		double beforeTransferX = -(fieldY - CONST_HALF_FIELD_WIDTH);
		double beforeTransferY = -(fieldX - CONST_HALF_FIELD_HEIGHT);
				
		double scaledX = beforeTransferX / CONST_FIELD_WIDTH * panelWidth;
		double scaledY = beforeTransferY / CONST_FIELD_HEIGHT * panelHeight;
		
		double[] result = {scaledX, scaledY};
		return result;
	}
}
