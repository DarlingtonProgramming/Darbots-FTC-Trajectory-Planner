package org.darbots.trajectorypregen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
	
	private class NormalFrameMouseListener implements MouseListener{
		
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
	private class RightInfoDisplayPanel extends JPanel{
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
		private JFormattedTextField ClickXText, ClickYText;
		private JButton MouseClickAddButton;
		private JButton MouseClickToRobotButton;
		private JPanel PointControlPanel;
		private JScrollPane PointPane;
		private JList<RobotPoint2D> PointList;
		private JButton PointDelButton;
		
		public RightInfoDisplayPanel() {
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
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
					double val = ((Number) evt.getNewValue()).doubleValue();
					// TODO Auto-generated method stub
					if(evt.getSource() == RobotXText) {
						m_RobotPose.X = val;
					}else if(evt.getSource() == RobotYText) {
						m_RobotPose.Y = val;
					}else {
						m_RobotPose.setRotationZ(val);
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
			this.RobotDimensionPanel.setLayout(new BoxLayout(this.RobotDimensionPanel,BoxLayout.X_AXIS));
			this.RobotDimensionPanel.setBorder(BorderFactory.createTitledBorder("Robot Dimension"));
			
			NumberFormat WidthFormatter = NumberFormat.getInstance(), LengthFormatter = NumberFormat.getInstance();
			this.RobotWidthText = new JFormattedTextField(WidthFormatter);
			this.RobotLengthText = new JFormattedTextField(LengthFormatter);
			this.updateRobotDimension();
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
			this.MousePositionPanel.setLayout(new BoxLayout(this.MousePositionPanel,BoxLayout.X_AXIS));
			this.MousePositionPanel.setBorder(BorderFactory.createTitledBorder("MousePosition"));
			this.MouseClickLabel = new JLabel();
			this.MouseClickLabel.setText("Click:");
			PropertyChangeListener ClickPointTextListener = new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					// TODO Auto-generated method stub
					double val = ((Number) evt.getNewValue()).doubleValue();
					if(evt.getSource() == ClickXText) {
						m_ClickedPoint.X = val;
					}else { // if(evt.getSource() == ClickYText) {
						m_ClickedPoint.Y = val;
					}
					updateMouseFieldPos();
				}	
			};
			NumberFormat ClickXFormat = NumberFormat.getInstance(), ClickYFormat = NumberFormat.getInstance();
			this.ClickXText = new JFormattedTextField(ClickXFormat);
			this.ClickYText = new JFormattedTextField(ClickYFormat);
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
			this.MouseClickToRobotButton = new JButton();
			this.MouseClickToRobotButton.setText("RobotPos");
			this.MouseClickToRobotButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					m_RobotPose.X = m_ClickedPoint.X;
					m_RobotPose.Y = m_ClickedPoint.Y;
					updateRobotPosition();
				}
				
			});
			
			this.MousePositionPanel.add(this.MouseClickLabel);
			this.MousePositionPanel.add(this.ClickXText);
			this.MousePositionPanel.add(this.ClickYText);
			this.MousePositionPanel.add(this.MouseClickAddButton);
			this.MousePositionPanel.add(this.MouseClickToRobotButton);
			this.updateMouseFieldPos();
			
			this.ClickXText.addPropertyChangeListener("value",ClickPointTextListener);
			this.ClickYText.addPropertyChangeListener("value",ClickPointTextListener);
			
			this.add(MousePositionPanel);
			
			this.PointControlPanel = new JPanel();
			this.PointControlPanel.setBorder(BorderFactory.createTitledBorder("Points"));
			this.PointControlPanel.setLayout(new FlowLayout());
			this.PointPane = new JScrollPane();
			this.PointList = new JList<RobotPoint2D>();
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
			
			this.updatePoints();
			
			this.add(PointControlPanel);
		}
		
		public void updateRobotPosition() {
			this.RobotXText.setValue(this.m_RobotPose.X);
			this.RobotYText.setValue(this.m_RobotPose.Y);
			this.RobotRotZText.setValue(this.m_RobotPose.getRotationZ());
			getFieldPanel().RobotPose = this.m_RobotPose;
			getFieldPanel().repaint();
		}
		
		public void updateRobotDimension() {
			this.RobotWidthText.setValue(this.m_RobotWidth);
			this.RobotLengthText.setValue(this.m_RobotLength);
			getFieldPanel().robotWidth = this.m_RobotWidth;
			getFieldPanel().robotLength = this.m_RobotLength;
			getFieldPanel().repaint();
		}
		
		public void updateMouseFieldPos() {
			this.ClickXText.setValue(this.m_ClickedPoint.X);
			this.ClickYText.setValue(this.m_ClickedPoint.Y);
			this.updatePointsInFieldView();
		}
		
		public void updatePoints() {
			RobotPoint2D[] mArr = new RobotPoint2D[this.m_Points.size()];
			for(int i = 0; i < this.m_Points.size(); i++) {
				mArr[i] = this.m_Points.get(i);
			}
			this.PointList.setListData(mArr);
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
		this.setLayout(new FlowLayout());
		this.setSize(Width,Height);
		this.getContentPane().setLayout(new GridLayout(1,1));
		this.getContentPane().add(this.getFieldPanel());
		this.getContentPane().add(this.m_RightPanel);
	}
	
	private void __setupRightPanel() {
		this.m_RightPanel = this.new RightInfoDisplayPanel();
	}
}
