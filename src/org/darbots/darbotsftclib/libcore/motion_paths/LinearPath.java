package org.darbots.darbotsftclib.libcore.motion_paths;

import java.util.List;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.darbots.darbotsftclib.libcore.calculations.dimentionalcalculation.Robot2DPositionIndicator;
import org.darbots.darbotsftclib.libcore.calculations.dimentionalcalculation.RobotPoint2D;
import org.darbots.darbotsftclib.libcore.templates.path_related.PathType;
import org.darbots.darbotsftclib.libcore.templates.path_related.RobotOriginalPath;

public class LinearPath extends RobotOriginalPath{
	
	private PolynomialFunction m_LineFunction;
	
	public LinearPath(List<Robot2DPositionIndicator> ControlPoints) {
		super(ControlPoints);
	}

	@Override
	public int getMinControlPointNum() {
		return 1;
	}

	@Override
	public int getMaxControlPointNum() {
		return 1;
	}

	@Override
	protected boolean __canPlot() {
		return true;
	}

	@Override
	public RobotPoint2D getPointAtDistance(double Distance) {
		if(this.m_LineFunction == null) {
			return null;
		}else {
			
		}
	}

	@Override
	public double getPathTotalDistance() {
		return 0;
	}

	@Override
	public PathType getPathType() {
		return PathType.LinearPath;
	}

	@Override
	protected void __reCalculate() {
		
	}
	
}
