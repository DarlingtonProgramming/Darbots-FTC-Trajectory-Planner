package org.darbots.darbotsftclib.libcore.calculations.dimentional_calculation;

import java.util.ArrayList;

public class XYPlaneCalculations {
    public static final double CONST_180_OVER_PI = 180.0 / Math.PI;
    public static final double CONST_PI_OVER_180 = Math.PI / 180;
    public static final double[] ORIGIN_POINT_ARRAY = {0,0};
    public static final double VERY_SMALL = 0.00001;

    public static double[] rotatePointAroundFixedPoint_Deg(double[] point, double[] fixedPoint, double counterClockwiseAng) {
        double relativeY = point[1] - fixedPoint[1], relativeX = point[0] - fixedPoint[0];
        double deltaAng = Math.toRadians(counterClockwiseAng);
        double sinDeltaAng = Math.sin(deltaAng);
        double cosDeltaAng = Math.cos(deltaAng);
        double newX = relativeX * cosDeltaAng - relativeY * sinDeltaAng;
        double newY = relativeX * sinDeltaAng + relativeY * cosDeltaAng;
        double[] result = {newX + fixedPoint[0], newY + fixedPoint[1]};
        return result;
    }
    public static double[] rotatePointAroundFixedPoint_Rad(double[] point, double[] fixedPoint, double counterClockwiseAng){
        double relativeY = point[1] - fixedPoint[1], relativeX = point[0] - fixedPoint[0];
        double deltaAng = counterClockwiseAng;
        double sinDeltaAng = Math.sin(deltaAng);
        double cosDeltaAng = Math.cos(deltaAng);
        double newX = relativeX * cosDeltaAng - relativeY * sinDeltaAng;
        double newY = relativeX * sinDeltaAng + relativeY * cosDeltaAng;
        double[] result = {newX + fixedPoint[0], newY + fixedPoint[1]};
        return result;
    }

    public static RobotPoint2D getRelativePosition(RobotPose2D PerspectiveOrigin, RobotPoint2D Target){
        //First step - move the Perspective Origin to the Origin of the Axis.
        double[] targetPoint = {Target.X - PerspectiveOrigin.X,Target.Y - PerspectiveOrigin.Y};
        //Second step - rotate the targetPoint so that the coordinate system (X and Z scalars) of the Perspective Origin overlaps with the Field Coordinate.
        //We are basically rotating field coordinate here.
        double[] origin = {0,0};
        double[] rotatedTargetPoint = rotatePointAroundFixedPoint_Deg(targetPoint,origin,-PerspectiveOrigin.getRotationZ());

        return new RobotPoint2D(rotatedTargetPoint[0],rotatedTargetPoint[1]);
    }

    public static RobotPoint2D getAbsolutePosition(RobotPose2D PerspectiveOrigin, RobotPoint2D RelativePosition){
        //First Step - rotate the coordinates back.
        double[] origin = {0,0};
        double[] relativeTargetPoint = {RelativePosition.X,RelativePosition.Y};
        double[] rotatedTargetPoint = rotatePointAroundFixedPoint_Deg(relativeTargetPoint,origin,PerspectiveOrigin.getRotationZ());
        //Second Step - move the PerspectiveOrigin back to the Absolute Point on the Field.
        double[] movedTargetPoint = {rotatedTargetPoint[0] + PerspectiveOrigin.X,rotatedTargetPoint[1] + PerspectiveOrigin.Y};

        return new RobotPoint2D(movedTargetPoint[0],movedTargetPoint[1]);
    }

    public static RobotPose2D getRelativePosition(RobotPose2D PerspectiveOrigin, RobotPose2D Target){
        //First step - move the Perspective Origin to the Origin of the Axis.
        double[] targetPoint = {Target.X - PerspectiveOrigin.X,Target.Y - PerspectiveOrigin.Y};
        //Second step - rotate the targetPoint so that the coordinate system (X and Z scalars) of the Perspective Origin overlaps with the Field Coordinate.
        //We are basically rotating field coordinate here.
        double[] origin = {0,0};
        double[] rotatedTargetPoint = rotatePointAroundFixedPoint_Deg(targetPoint,origin,-PerspectiveOrigin.getRotationZ());
        //Third step - calculate relative delta Rotation Y;
        double deltaRotZ = normalizeDeg(Target.getRotationZ() - PerspectiveOrigin.getRotationZ());

        return new RobotPose2D(rotatedTargetPoint[0],rotatedTargetPoint[1],deltaRotZ);
    }

    public static RobotPose2D getAbsolutePosition(RobotPose2D PerspectiveOrigin, RobotPose2D RelativePosition){
        //First Step - calculate absolute Rotation Y.
        double absRotZ = normalizeDeg(RelativePosition.getRotationZ() + PerspectiveOrigin.getRotationZ());
        //Second Step - rotate the coordinates back.
        double[] origin = {0,0};
        double[] relativeTargetPoint = {RelativePosition.X,RelativePosition.Y};
        double[] rotatedTargetPoint = rotatePointAroundFixedPoint_Deg(relativeTargetPoint,origin,PerspectiveOrigin.getRotationZ());
        //Third Step - move the PerspectiveOrigin back to the Absolute Point on the Field.
        double[] movedTargetPoint = {rotatedTargetPoint[0] + PerspectiveOrigin.X,rotatedTargetPoint[1] + PerspectiveOrigin.Y};

        return new RobotPose2D(movedTargetPoint[0],movedTargetPoint[1],absRotZ);
    }

    public static RobotVector2D getRelativePosition(RobotVector2D PerspectiveOrigin, RobotVector2D Target){
        //First step - move the Perspective Origin to the Origin of the Axis.
        double[] targetPoint = {Target.X - PerspectiveOrigin.X,Target.Y - PerspectiveOrigin.Y};
        //Second step - rotate the targetPoint so that the coordinate system (X and Z scalars) of the Perspective Origin overlaps with the Field Coordinate.
        //We are basically rotating field coordinate here.
        double[] origin = {0,0};
        double[] rotatedTargetPoint = rotatePointAroundFixedPoint_Deg(targetPoint,origin,-PerspectiveOrigin.getRotationZ());
        //Third step - calculate relative delta Rotation Y;
        double deltaRotZ = Target.getRotationZ() - PerspectiveOrigin.getRotationZ();

        return new RobotVector2D(rotatedTargetPoint[0],rotatedTargetPoint[1],deltaRotZ);
    }

    public static RobotVector2D getAbsolutePosition(RobotVector2D PerspectiveOrigin, RobotVector2D RelativePosition){
        //First Step - calculate absolute Rotation Y.
        double absRotZ = RelativePosition.getRotationZ() + PerspectiveOrigin.getRotationZ();
        //Second Step - rotate the coordinates back.
        double[] origin = {0,0};
        double[] relativeTargetPoint = {RelativePosition.X,RelativePosition.Y};
        double[] rotatedTargetPoint = rotatePointAroundFixedPoint_Deg(relativeTargetPoint,origin,PerspectiveOrigin.getRotationZ());
        //Third Step - move the PerspectiveOrigin back to the Absolute Point on the Field.
        double[] movedTargetPoint = {rotatedTargetPoint[0] + PerspectiveOrigin.X,rotatedTargetPoint[1] + PerspectiveOrigin.Y};

        return new RobotVector2D(movedTargetPoint[0],movedTargetPoint[1],absRotZ);
    }

    public static double chooseAngleFromRange(double[] angleList, double angleSmallestRange, double angleBiggestRange) {
        for(int i=0;i<angleList.length;i++) {
            if(angleList[i] >= angleSmallestRange && angleList[i] <= angleBiggestRange) {
                return angleList[i];
            }
        }
        return angleList[0];
    }

    public static double normalizeRad(double Rad) {
        double tempRad = Rad % (Math.PI * 2);
        if(tempRad >= Math.PI){
            tempRad -= (Math.PI) * 2;
        }
        return tempRad;
    }

    public static float normalizeRad(float Rad){
        float tempRad = (float) (Rad % (Math.PI * 2));
        if(tempRad >= Math.PI){
            tempRad -= (Math.PI) * 2;
        }
        return tempRad;
    }

    public static double normalizeDeg(double Deg) {
        double tempDeg = Deg % 360;
        if(tempDeg >= 180){
            tempDeg -= 360;
        }
        return tempDeg;
    }

    public static float normalizeDeg(float Deg) {
        float tempDeg = Deg % 360;
        if(tempDeg >= 180){
            tempDeg -= 360;
        }
        return tempDeg;
    }

    public static boolean isPointAtCircleEdge(RobotPoint2D circleCenter, double circleRadius, RobotPoint2D point){
        if(point.distanceTo(circleCenter) <= VERY_SMALL){
            return true;
        }else{
            return false;
        }
    }

    public static ArrayList<RobotPoint2D> verticleLineCircleIntersections(RobotPoint2D circleCenter, double circleRadius, double fixedX){
        double x1 = fixedX - circleCenter.X;
        double ySquared = Math.pow(circleRadius,2) - Math.pow(x1,2);
        ArrayList<RobotPoint2D> allPoints = new ArrayList<RobotPoint2D>();
        if(ySquared < 0){
            return allPoints;
        }
        if(ySquared == 0){
            RobotPoint2D root = new RobotPoint2D(fixedX,circleCenter.Y);
            allPoints.add(root);
        }else{
            double sqrtYSquared = Math.sqrt(ySquared);
            RobotPoint2D root1 = new RobotPoint2D(fixedX,sqrtYSquared + circleCenter.Y);
            allPoints.add(root1);
            RobotPoint2D root2 = new RobotPoint2D(fixedX,-sqrtYSquared + circleCenter.Y);
            allPoints.add(root2);
        }
        return allPoints;
    }

    public static ArrayList<RobotPoint2D> horizontalLineCircleIntersections(RobotPoint2D circleCenter, double circleRadius, double fixedY){
        double y1 = fixedY - circleCenter.Y;
        double xSquared = Math.pow(circleRadius,2) - Math.pow(y1,2);
        ArrayList<RobotPoint2D> allPoints = new ArrayList<RobotPoint2D>();
        if(xSquared < 0){
            return allPoints;
        }
        if(xSquared == 0){
            RobotPoint2D root = new RobotPoint2D(circleCenter.X,fixedY);
            allPoints.add(root);
        }else{
            double sqrtXSquared = Math.sqrt(xSquared);
            RobotPoint2D root1 = new RobotPoint2D(sqrtXSquared + circleCenter.X,fixedY);
            allPoints.add(root1);
            RobotPoint2D root2 = new RobotPoint2D(-sqrtXSquared + circleCenter.X,fixedY);
            allPoints.add(root2);
        }
        return allPoints;
    }

    public static ArrayList<RobotPoint2D> lineSegmentCircleIntersections(RobotPoint2D circleCenter, double circleRadius, RobotPoint2D linePoint1, RobotPoint2D linePoint2){
        RobotPoint2D boundingBoxMin = new RobotPoint2D(
                Math.min(linePoint1.X,linePoint2.X),
                Math.min(linePoint1.Y,linePoint2.Y)
        );
        RobotPoint2D boundingBoxMax = new RobotPoint2D(
                Math.max(linePoint1.X,linePoint2.X),
                Math.max(linePoint1.Y,linePoint2.Y)
        );

        if(linePoint1.X == linePoint2.X || linePoint1.Y == linePoint2.Y){
            ArrayList<RobotPoint2D> resultArray = null;
            if(linePoint1.X == linePoint2.X && linePoint1.Y == linePoint2.Y){
                resultArray = new ArrayList<RobotPoint2D>();
                if(isPointAtCircleEdge(circleCenter,circleRadius,linePoint1)){
                    resultArray.add(linePoint1);
                }
            }else if(linePoint1.X == linePoint2.X){
                resultArray = verticleLineCircleIntersections(circleCenter,circleRadius,linePoint1.X);
            }else { //linePoint1.Y == linePoint2.Y
                resultArray = horizontalLineCircleIntersections(circleCenter,circleRadius,linePoint1.Y);
            }
            ArrayList<RobotPoint2D> allPoints = new ArrayList<RobotPoint2D>();
            for(RobotPoint2D i : resultArray){
                if(isInBoundingBox(i,boundingBoxMin,boundingBoxMax)){
                    allPoints.add(i);
                }
            }
            return allPoints;
        }

        double x1 = linePoint1.X - circleCenter.X;
        double y1 = linePoint1.Y - circleCenter.Y;

        double m1 = (linePoint2.Y - linePoint1.Y) / (linePoint2.X - linePoint1.X);
        double quadraticA = 1.0 + Math.pow(m1,2);
        double quadraticB = (2.0 * m1 * y1) - (2.0 * Math.pow(m1,2) * x1);
        double quadraticC = ((Math.pow(m1,2) * Math.pow(x1,2))) - (2.0 * y1 * m1 * x1) + Math.pow(y1,2) - Math.pow(circleRadius,2);
        double quadraticDelta = Math.pow(quadraticB,2) - (4.0 * quadraticA * quadraticC);

        ArrayList<RobotPoint2D> allPoints = new ArrayList<RobotPoint2D>();
        if(quadraticDelta > 0) {
            double xRoot1 = (-quadraticB + Math.sqrt(quadraticDelta)) / (2.0 * quadraticA);
            double yRoot1 = m1 * (xRoot1 - x1) + y1;
            xRoot1 += circleCenter.X;
            yRoot1 += circleCenter.Y;
            RobotPoint2D root1 = new RobotPoint2D(xRoot1, yRoot1);
            if (isInBoundingBox(root1, boundingBoxMin, boundingBoxMax)) {
                allPoints.add(root1);
            }
            double xRoot2 = (-quadraticB - Math.sqrt(quadraticDelta)) / (2.0 * quadraticA);
            double yRoot2 = m1 * (xRoot2 - x1) + y1;
            xRoot2 += circleCenter.X;
            yRoot2 += circleCenter.Y;
            RobotPoint2D root2 = new RobotPoint2D(xRoot2,yRoot2);
            if(isInBoundingBox(root2,boundingBoxMin,boundingBoxMax)){
                allPoints.add(root2);
            }
        }else if(quadraticDelta == 0){
            double xRoot1 = (-quadraticB) / (2.0 * quadraticA);
            double yRoot1 = m1 * (xRoot1 - x1) + y1;
            xRoot1 += circleCenter.X;
            yRoot1 += circleCenter.Y;
            RobotPoint2D root1 = new RobotPoint2D(xRoot1, yRoot1);
            if (isInBoundingBox(root1, boundingBoxMin, boundingBoxMax)) {
                allPoints.add(root1);
            }
        }
        return allPoints;
    }
    public static boolean isInBoundingBox(RobotPoint2D point, RobotPoint2D minPoint, RobotPoint2D maxPoint){
        if(point.X >= minPoint.X && point.X <= maxPoint.X && point.Y >= minPoint.Y && point.Y <= maxPoint.Y){
            return true;
        }else{
            return false;
        }
    }

    public static RobotPoint2D nearestPointOnLineSegment(RobotPoint2D point, RobotPoint2D linePoint1, RobotPoint2D linePoint2){
        if(linePoint1.X == linePoint2.X && linePoint1.Y == linePoint2.Y){
            return null;
        }
        RobotPoint2D pointResult;
        if(linePoint1.Y == linePoint2.Y){
            pointResult = new RobotPoint2D(point.X,linePoint1.Y);
        }else if(linePoint1.X == linePoint2.X){
            pointResult = new RobotPoint2D(linePoint1.X,point.Y);
        }else{
            double m = (linePoint2.Y - linePoint1.Y) / (linePoint2.X - linePoint1.X);
            double b = linePoint1.Y - linePoint1.X * m;
            double n = -1.0 / m;
            double c = point.Y - point.X * n;
            double nearestPointX = (c-b) / (m-n);
            pointResult = new RobotPoint2D(nearestPointX,m * nearestPointX + b);
        }
        RobotPoint2D boundingBoxMin = new RobotPoint2D(
                Math.min(linePoint1.X,linePoint2.X),
                Math.min(linePoint1.Y,linePoint2.Y)
        );
        RobotPoint2D boundingBoxMax = new RobotPoint2D(
                Math.max(linePoint1.X,linePoint2.X),
                Math.max(linePoint1.Y,linePoint2.Y)
        );
        if(isInBoundingBox(pointResult,boundingBoxMin,boundingBoxMax)){
            return pointResult;
        }else{
            return null;
        }
    }
}