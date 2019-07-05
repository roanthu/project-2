/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import com.sun.j3d.utils.geometry.Text2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.io.Serializable;
import javafx.geometry.Point3D;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.LineArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author Roan Thu
 */
public class Line extends Canh implements Serializable{
    private static final long serialVersionUID = 1L;
	private Line2D.Double l = new Line2D.Double();
	final int barb = 10;
	final int r = 15;
	final double phi = Math.PI / 6;
        private Point3d p1,p2;
    @Override
        public void drawLine(Graphics2D g, Point p1, Point p2, int size) {
		String c = "";
		if (super.getCost() < 0) {
			c = "";
		} else
			c = String.valueOf(super.getCost());
		g.setColor(super.getColorLine());
		g.setStroke(new BasicStroke(size));
		double theta = Math.atan2(p2.y - p1.y, p2.x - p1.x);
		g.draw(l);
		

		g.setColor(super.getColorCost());
		g.drawString(c, (int) (Math.abs(p1.x + p2.x) / 2),
				(int) (p1.y + p2.y) / 2);
	}
    @Override
        public boolean containerPoint(Point p) {
		Polygon poly = createPolygon(l);
		for (int i = 0; i < poly.npoints; i++) {
                    
			double temp = (p.x - poly.xpoints[i])
					* (poly.ypoints[(i + 1) % poly.npoints] - poly.ypoints[i])
					- (p.y - poly.ypoints[i])
					* (poly.xpoints[(i + 1) % poly.npoints] - poly.xpoints[i]);
			if (temp < 0)
				return false;
		}
		return true;
	}
        private Polygon createPolygon(Line2D line) {
		int barb = 5;
		double phi = Math.PI / 2;
		double theta = Math.atan2(line.getY2() - line.getY1(), line.getX2()
				- line.getX1());
		int x[] = new int[4];
		int y[] = new int[4];
		x[0] = (int) (line.getX1() - barb * Math.cos(theta + phi));
		y[0] = (int) (line.getY1() - barb * Math.sin(theta + phi));
		x[1] = (int) (line.getX1() - barb * Math.cos(theta - phi));
		y[1] = (int) (line.getY1() - barb * Math.sin(theta - phi));

		x[2] = (int) (line.getX2() - barb * Math.cos(theta - phi));
		y[2] = (int) (line.getY2() - barb * Math.sin(theta - phi));
		x[3] = (int) (line.getX2() - barb * Math.cos(theta + phi));
		y[3] = (int) (line.getY2() - barb * Math.sin(theta + phi));
		Polygon poly = new Polygon(x, y, 4);
		return poly;
	}
        public Line2D.Double getL() {
		return l;
	}
    @Override
    public void setL(Line2D.Double l) {
        this.l = l;
    }

    @Override
    public void setL(Shape l) {
        
    }
    
    /// 3DDD
    private LineArray axisXLines;
    private Shape3D shape;
    private Text2D text;


    public Text2D getText() {
        return text;
    }

    public void setText(Text2D text) {
        this.text = text;
    }
    public static TransformGroup group = new TransformGroup();
    public Shape3D getShape() {
        return shape;
    }

    public void setShape(Shape3D shape) {
        this.shape = shape;
    }
    

    public LineArray getAxisXLines() {
        return axisXLines;
    }

    public void setAxisXLines(LineArray axisXLines) {
        this.axisXLines = axisXLines;
    }


    public static TransformGroup getGroup() {
        return group;
    }

    public static void setGroup(TransformGroup group) {
        Line.group = group;
    }
    
    @Override
    public void creatLine(BranchGroup branchGroup){
        if(super.isIsHide()==false){
        Color3f cor = new Color3f(super.getColorLine());
        Point3d p1 = super.p1;
        Point3d p2 = super.p2;
        LineArray axisXLines = new LineArray(2, LineArray.COORDINATES|LineArray.COLOR_3);
        axisXLines.setCoordinate(0, p1);axisXLines.setColor(0, cor);
        axisXLines.setCoordinate(1, p2);axisXLines.setColor(1, cor);
        this.shape = new Shape3D(axisXLines);
        //draw cost
        Text2D string = new Text2D(String.valueOf(super.getCost()),cor,"Helvetica", 10, Font.ITALIC);
        Transform3D transform = new Transform3D();
        transform.setTranslation(new Vector3d((p1.x+p2.x)/2,(p1.y+p2.y)/2, (p1.z+p2.z)/2));
        this.text = string;
        TransformGroup objTrans = new TransformGroup(transform);
        objTrans.addChild(string);
        
        super.getTempBranch().addChild(shape);
        super.getTempBranch().addChild(objTrans);
        branchGroup.addChild(super.getTempBranch());
        this.axisXLines = axisXLines;
        } 
    }
    public Line(Point3d p1, Point3d p2 ,int indexPointA,int indexPointB,int cost, Color colorLine) {
            super(p1,p2,indexPointA, indexPointB, cost,colorLine);
	}
    
}
