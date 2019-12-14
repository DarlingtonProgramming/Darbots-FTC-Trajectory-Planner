package org.darbots.trajectorypregen;

import java.awt.Dimension;
import java.awt.Graphics;

public interface OnFieldPlottable {
	void onPlot(FTCFieldPanel fieldPanel, Graphics g, Dimension fieldDimension);
}
