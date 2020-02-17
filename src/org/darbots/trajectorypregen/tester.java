package org.darbots.trajectorypregen;

import javax.swing.JFrame;

import org.darbots.darbotsftclib.libcore.calculations.dimentional_calculation.RobotPoint2D;
import org.darbots.darbotsftclib.libcore.calculations.dimentional_calculation.RobotPose2D;
import org.darbots.darbotsftclib.libcore.calculations.dimentional_calculation.XYPlaneCalculations;
import org.darbots.darbotsftclib.season_specific.skystone.SkyStoneCoordinates;

public class tester {
	public static void main(String[] args) {
		/*
		JFrame FrameToTest = new NormalFrame(1500,750);
		FrameToTest.setTitle("Darbots-Field-Point-Specifier");
		FrameToTest.setVisible(true);
		FrameToTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return;
		*/
		RobotPoint2D AUTONOMOUS_CLAW_RIGHT_POSITION_WHEN_DOWN = new RobotPoint2D(13.5,-24.9);
		RobotPoint2D lastStone = SkyStoneCoordinates.STONE_AUDIENCE_RED;
		RobotPose2D supposedPose = XYPlaneCalculations.getAbsolutePosition(-180, AUTONOMOUS_CLAW_RIGHT_POSITION_WHEN_DOWN, lastStone);
		System.out.println("Transposed Position" + supposedPose.toString());
	}
}
