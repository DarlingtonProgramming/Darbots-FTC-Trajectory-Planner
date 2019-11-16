package org.darbots.darbotsftclib.libcore.templates.path_related;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.darbots.darbotsftclib.libcore.calculations.dimentionalcalculation.Robot2DPositionIndicator;
import org.darbots.darbotsftclib.libcore.calculations.dimentionalcalculation.RobotPoint2D;

public abstract class RobotOriginalPath implements List<Robot2DPositionIndicator> {
	private ArrayList<Robot2DPositionIndicator> m_ControlPoints;
	public abstract int getMinControlPointNum();
	public abstract int getMaxControlPointNum();
	protected abstract boolean __canPlot();
	public abstract PathType getPathType();
	protected abstract void __reCalculate();
	public abstract RobotPoint2D getPointAtDistance(double Distance);
	public abstract double getPathTotalDistance();
	
	public RobotOriginalPath(List<Robot2DPositionIndicator> ControlPoints) {
		this.m_ControlPoints = new ArrayList<Robot2DPositionIndicator>();
		this.addAll(ControlPoints);
	}
	
	public boolean canPlot() {
		return (this.size() >= this.getMinControlPointNum() && this.size() <= this.getMaxControlPointNum() && this.__canPlot());
	}
	
	public RobotPoint2D getPointAtProgress(double ProgressRatio) {
		return this.getPointAtDistance(ProgressRatio * this.getPathTotalDistance());
	}
	
	public boolean isValid() {
		boolean InvalidExp = (this.getPathTotalDistance() == 0);
		return !InvalidExp;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.m_ControlPoints.size();
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.m_ControlPoints.isEmpty();
	}
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return this.m_ControlPoints.contains(o);
	}
	@Override
	public Iterator<Robot2DPositionIndicator> iterator() {
		// TODO Auto-generated method stub
		return this.m_ControlPoints.iterator();
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return this.m_ControlPoints.toArray();
	}
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return this.m_ControlPoints.toArray(a);
	}
	@Override
	public boolean add(Robot2DPositionIndicator e) {
		// TODO Auto-generated method stub
		boolean result = this.m_ControlPoints.add(e);
		this.__reCalculate();
		return result;
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		boolean result = this.m_ControlPoints.remove(o);
		this.__reCalculate();
		return result;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return this.m_ControlPoints.containsAll(c);
	}
	@Override
	public boolean addAll(Collection<? extends Robot2DPositionIndicator> c) {
		// TODO Auto-generated method stub
		boolean result = this.m_ControlPoints.addAll(c);
		this.__reCalculate();
		return result;
	}
	@Override
	public boolean addAll(int index, Collection<? extends Robot2DPositionIndicator> c) {
		// TODO Auto-generated method stub
		boolean result = this.m_ControlPoints.addAll(index,c);
		this.__reCalculate();
		return result;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		boolean result = this.m_ControlPoints.removeAll(c);
		this.__reCalculate();
		return result;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		boolean result = this.m_ControlPoints.retainAll(c);
		this.__reCalculate();
		return result;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.m_ControlPoints.clear();
		this.__reCalculate();
	}
	@Override
	public Robot2DPositionIndicator get(int index) {
		// TODO Auto-generated method stub
		return this.m_ControlPoints.get(index);
	}
	@Override
	public Robot2DPositionIndicator set(int index, Robot2DPositionIndicator element) {
		// TODO Auto-generated method stub
		Robot2DPositionIndicator result = this.m_ControlPoints.set(index, element);
		this.__reCalculate();
		return result;
	}
	@Override
	public void add(int index, Robot2DPositionIndicator element) {
		this.m_ControlPoints.add(index,element);
		this.__reCalculate();
	}
	@Override
	public Robot2DPositionIndicator remove(int index) {
		Robot2DPositionIndicator result = this.m_ControlPoints.remove(index);
		this.__reCalculate();
		return result;
	}
	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return this.m_ControlPoints.indexOf(o);
	}
	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return this.m_ControlPoints.lastIndexOf(o);
	}
	@Override
	public ListIterator<Robot2DPositionIndicator> listIterator() {
		// TODO Auto-generated method stub
		return this.m_ControlPoints.listIterator();
	}
	@Override
	public ListIterator<Robot2DPositionIndicator> listIterator(int index) {
		// TODO Auto-generated method stub
		return this.m_ControlPoints.listIterator(index);
	}
	@Override
	public List<Robot2DPositionIndicator> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return this.m_ControlPoints.subList(fromIndex, toIndex);
	}
	
	public static RobotOriginalPath getInstance(PathType type, ArrayList<RobotPoint2D> controlPoints) {
		if(type.equals(PathType.LinearPath)) {
			return null;
		}
		return null;
	}
	
	
}
