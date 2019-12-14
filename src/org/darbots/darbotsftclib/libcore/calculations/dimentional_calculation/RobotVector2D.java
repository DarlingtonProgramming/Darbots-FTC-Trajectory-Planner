/*
MIT License

Copyright (c) 2018 DarBots Collaborators

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/
package org.darbots.darbotsftclib.libcore.calculations.dimentional_calculation;


public class RobotVector2D {
    public double X;
    public double Y;
    protected double m_RotationZ;
    public RobotVector2D(double X, double Y, double ZRotation){
        this.X = X;
        this.Y = Y;
        this.m_RotationZ = ZRotation;
    }
    public RobotVector2D(RobotPoint2D Point, double ZRotation) {
        this.X = Point.X;
        this.Y = Point.Y;
        this.m_RotationZ = ZRotation;
    }
    public RobotVector2D(RobotVector2D Pos2D){
        this.X = Pos2D.X;
        this.Y = Pos2D.Y;
        this.m_RotationZ = Pos2D.m_RotationZ;
    }
    public void setValues(double X, double Y, double RotationZ){
        this.X = X;
        this.Y = Y;
        this.m_RotationZ = RotationZ;
    }
    public void setValues(RobotVector2D pose2D){
        this.X = pose2D.X;
        this.Y = pose2D.Y;
        this.m_RotationZ = pose2D.m_RotationZ;
    }
    public void offsetValues(double X, double Y, double RotationZ){
        this.X += X;
        this.Y += Y;
        this.m_RotationZ += RotationZ;
    }
    public void offsetValues(RobotVector2D pose2D){
        this.offsetValues(pose2D.X,pose2D.Y,pose2D.getRotationZ());
    }
    public double getDistanceToOrigin(){
        return (Math.sqrt(Math.pow(this.X,2) + Math.pow(this.Y,2)));
    }
    public double getRotationZ(){
        return this.m_RotationZ;
    }
    public void setRotationZ(double RotationZ){
        this.m_RotationZ = RotationZ;
    }
    public RobotPoint2D toPoint2D() {
        return new RobotPoint2D(this);
    }
}