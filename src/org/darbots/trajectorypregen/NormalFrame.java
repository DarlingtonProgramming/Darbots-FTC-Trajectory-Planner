package org.darbots.trajectorypregen;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.darbots.darbotsftclib.libcore.calculations.dimentional_calculation.RobotPoint2D;
import org.darbots.darbotsftclib.libcore.calculations.dimentional_calculation.RobotPose2D;

public class NormalFrame extends CommonFramework {
	public static final Color CONST_NORMAL_POINT_COLOR = Color.BLACK;
	public static final Color CONST_CLICK_POINT_COLOR = Color.BLUE;
	
	public class NormalFrameMouseListener implements MouseListener{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			Point clickPoint = e.getPoint();
			double[] fieldPoint = getFieldPanel().transferToFieldPoint(clickPoint.x, clickPoint.y);
			m_RightPanel.m_ClickedPoint.X = fieldPoint[0];
			m_RightPanel.m_ClickedPoint.Y = fieldPoint[1];
			m_RightPanel.updateMouseFieldPos();
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
	public class RightInfoDisplayPanel extends JPanel{
		private RobotPose2D m_RobotPose;
		private double m_RobotWidth, m_RobotLength;
		private ArrayList<RobotPoint2D> m_Points;
		private RobotPoint2D m_ClickedPoint;
		
		private JPanel RobotPositionPanel;
		private JFormattedTextField RobotXText, RobotYText, RobotRotZText;
		private JPanel RobotDimensionPanel;
		private JFormattedTextField RobotWidthText, RobotLengthText;
		private JPanel MousePositionPanel;
		private JLabel MouseClickLabel;
		private JButton MouseClickAddButton;
		private JPanel PointControlPanel;
		private JScrollPane PointPane;
		private JList<RobotPoint2D> PointList;
		private JButton PointDelButton;
		
		public RightInfoDisplayPanel() {
			super();
			this.__initializeVals();
			this.__initializeControls();
		}
		
		public RobotPose2D getRobotPose() {
			return this.m_RobotPose;
		}
		
		public double getRobotWidth() {
			return this.m_RobotWidth;
		}
		
		public void setRobotWidth(double width) {
			this.m_RobotWidth = Math.abs(width);
		}
		
		public double getRobotLength() {
			return this.m_RobotLength;
		}
		
		public void setRobotLength(double height) {
			this.m_RobotLength = Math.abs(height);
		}
		
		public ArrayList<RobotPoint2D> getPoints(){
			return this.m_Points;
		}
		
		public RobotPoint2D getClickedPoint() {
			return this.m_ClickedPoint;
		}
		
		private void __initializeVals() {
			this.m_RobotPose = new RobotPose2D(0,0,0);
			this.m_RobotWidth = 0;
			this.m_RobotLength = 0;
			this.m_Points = new ArrayList<RobotPoint2D>();
			this.m_ClickedPoint = new RobotPoint2D(0,0);
		}
		
		private void __initializeControls() {
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			
			this.RobotPositionPanel = new JPanel();
			this.RobotPositionPanel.setLayout(new BoxLayout(this.RobotPositionPanel,BoxLayout.X_AXIS));
			this.RobotPositionPanel.setBorder(BorderFactory.createTitledBorder("Robot Position"));
			
			NumberFormat RobotXFormatter = NumberFormat.getInstance(), RobotYFormatter = NumberFormat.getInstance(), RobotZRotFormatter = NumberFormat.getInstance();
			this.RobotXText = new JFormattedTextField(RobotXFormatter);
			this.RobotYText = new JFormattedTextField(RobotYFormatter);
			this.RobotRotZText = new JFormattedTextField(RobotZRotFormatter);
			
			this.updateRobotPosition();
			
			PropertyChangeListener RobotPositionListener = new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					// TODO Auto-generated method stub
					if(evt.getSource() == RobotXText) {
						m_RobotPose.X = ((Number) evt.getNewValue()).doubleValue();
					}else if(evt.getSource() == RobotYText) {
						m_RobotPose.Y = ((Number) evt.getNewValue()).doubleValue();
					}else {
						m_RobotPose.setRotationZ(((Number) evt.getNewValue()).doubleValue());
					}
					updateRobotPosition();
				}
			};
			RobotXText.addPropertyChangeListener("value", RobotPositionListener);
			RobotYText.addPropertyChangeListener("value", RobotPositionListener);
			RobotRotZText.addPropertyChangeListener("value", RobotPositionListener);
			
			this.RobotPositionPanel.add(RobotXText);
			this.RobotPositionPanel.add(RobotYText);
			this.RobotPositionPanel.add(RobotRotZText);
			
			this.add(RobotPositionPanel);
			
			
			this.RobotDimensionPanel = new JPanel();
			this.RobotDimensionPanel.setLayout(new BoxLayout(this.RobotPositionPanel,BoxLayout.X_AXIS));
			this.RobotDimensionPanel.setBorder(BorderFactory.createTitledBorder("Robot Dimension"));
			
			NumberFormat WidthFormatter = NumberFormat.getInstance(), LengthFormatter = NumberFormat.getInstance();
			this.RobotWidthText = new JFormattedTextField(WidthFormatter);
			this.RobotLengthText = new JFormattedTextField(LengthFormatter);
			PropertyChangeListener RobotDimensionListener = new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					// TODO Auto-generated method stub
					if(evt.getSource() == RobotWidthText) {
						m_RobotWidth = Math.abs(((Number) evt.getNewValue()).doubleValue());
					}else {
						m_RobotLength = Math.abs(((Number) evt.getNewValue()).doubleValue());
					}
					updateRobotDimension();
				}	
			};
			this.RobotWidthText.addPropertyChangeListener("value", RobotDimensionListener);
			this.RobotLengthText.addPropertyChangeListener("value", RobotDimensionListener);
			
			this.RobotDimensionPanel.add(RobotWidthText);
			this.RobotDimensionPanel.add(RobotLengthText);
			
			this.add(RobotDimensionPanel);
			
			this.MousePositionPanel = new JPanel();
			this.MousePositionPanel.setLayout(new FlowLayout());
			this.MousePositionPanel.setBorder(BorderFactory.createTitledBorder("MousePosition"));
			this.MouseClickLabel = new JLabel();
			this.MouseClickAddButton = new JButton();
			this.MouseClickAddButton.setText("Add");
			this.MouseClickAddButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					m_Points.add(new RobotPoint2D(m_ClickedPoint));
					updatePoints();
				}
				
			});
			this.MousePositionPanel.add(this.MouseClickLabel);
			this.MousePositionPanel.add(this.MouseClickAddButton);
			this.updateMouseFieldPos();
			
			this.add(MousePositionPanel);
			
			this.PointControlPanel = new JPanel();
			this.PointControlPanel.setBorder(BorderFactory.createTitledBorder("Points"));
			this.PointControlPanel.setLayout(new FlowLayout());
			this.PointPane = new JScrollPane();
			this.PointList = new JList();
			this.PointList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.PointPane.getViewport().add(PointList);
			this.PointDelButton = new JButton();
			this.PointDelButton.setText("Delete");
			this.PointDelButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					RobotPoint2D selectedItem = PointList.getSelectedValue();
					if(selectedItem != null) {
						m_Points.remove(selectedItem);
						updatePoints();
					}
				}
				
			});
			this.PointControlPanel.add(this.PointPane);
			this.PointControlPanel.add(PointDelButton);
			
			this.add(PointControlPanel);
		}
		
		public void updateRobotPosition() {
			this.RobotXText.setValue(this.m_RobotPose.X);
			this.RobotYText.setValue(this.m_RobotPose.Y);
			this.RobotRotZText.setValue(this.m_RobotPose.getRotationZ());
			getFieldPanel().RobotPose.X = this.m_RobotPose.X;
			getFieldPanel().RobotPose.Y = this.m_RobotPose.Y;
			getFieldPanel().RobotPose.setRotationZ(this.m_RobotPose.getRotationZ());
		}
		
		public void updateRobotDimension() {
			this.RobotWidthText.setValue(this.m_RobotWidth);
			this.RobotLengthText.setValue(this.m_RobotLength);
			getFieldPanel().robotWidth = this.m_RobotWidth;
			getFieldPanel().robotLength = this.m_RobotLength;
		}
		
		public void updateMouseFieldPos() {
			this.MouseClickLabel.setText("Click: " + "(" + this.m_ClickedPoint.X + "," + this.m_ClickedPoint.Y + ")");
		}
		
		public void updatePoints() {
			this.PointList.setListData((RobotPoint2D[]) this.m_Points.toArray());
			this.updatePointsInFieldView();
		}
		
		private void updatePointsInFieldView() {
			getFieldPanel().getPointsToPlot().clear();
			for(RobotPoint2D thisPoint : this.m_Points) {
				PointOnMap thisPaintPoint = new PointOnMap(thisPoint,CONST_NORMAL_POINT_COLOR);
				getFieldPanel().getPointsToPlot().add(thisPaintPoint);
			}
			getFieldPanel().getPointsToPlot().add(new PointOnMap(this.m_ClickedPoint,CONST_CLICK_POINT_COLOR));
			getFieldPanel().repaint();
		}
	}
	
	private RightInfoDisplayPanel m_RightPanel;
	public NormalFrame(int Width, int Height) {
		this.__setupPanel();
		this.__setupRightPanel();
		this.getFieldPanel().addMouseListener(new NormalFrameMouseListener());
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.setSize(Width,Height);
		this.getContentPane().setLayout(new GridLayout(1,1));
		this.getContentPane().add(this.getFieldPanel());
	}
	
	private void __setupRightPanel() {
		this.m_RightPanel = new RightInfoDisplayPanel();
	}
}
