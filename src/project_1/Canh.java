/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import com.sun.j3d.utils.geometry.Text2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.io.Serializable;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Group;
import javax.media.j3d.LineArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;

/**
 *
 * @author Roan Thu
 */
public abstract class Canh implements Serializable{
    private static final long serialVersionUID = 1L;	
    private int indexPointA, indexPointB;
	private int cost;
        private boolean isHide;
        private Color colorCost, colorLine;
        Point3d p1,p2;

    public Point3d getP1() {
        return p1;
    }

    public void setP1(Point3d p1) {
        this.p1 = p1;
    }

    public Point3d getP2() {
        return p2;
    }

    public void setP2(Point3d p2) {
        this.p2 = p2;
    }
        
    public BranchGroup getTempBranch() {
        return tempBranch;
    }

    public void setTempBranch(BranchGroup tempBranch) {
        this.tempBranch = tempBranch;
    }
        private BranchGroup tempBranch = new BranchGroup();

    public Color getColorCost() {
        return colorCost;
    }

    public void setColorCost(Color colorCost) {
        this.colorCost = colorCost;
    }

    public Color getColorLine() {
        return colorLine;
    }

    public void setColorLine(Color colorLine) {
        this.colorLine = colorLine;
    }

    public boolean isIsHide() {
        return isHide;
    }

    public void setIsHide(boolean isHide) {
        this.isHide = isHide;
    }

	final int barb = 10;
	final int r = 15;
	final double phi = Math.PI / 6;

	public Canh(Point3d p1, Point3d p2, int indexPointA, int indexPointB, int cost, Color colorLine) {
		this.p1 = p1;
                this.p2 = p2;
                this.cost = cost;
		this.indexPointA = indexPointA;
		this.indexPointB = indexPointB;
                this.colorLine = colorLine;
                this.isHide = false;
                this.tempBranch = new BranchGroup();
                tempBranch.setCapability(BranchGroup.ALLOW_DETACH);
                tempBranch.setCapability(Group.ALLOW_CHILDREN_EXTEND);
	}
        public abstract void drawLine(Graphics2D g, Point p1, Point p2, int size);
        public abstract boolean containerPoint(Point p);
        

	public int getIndexPointA() {
		return indexPointA;
	}

	public void setIndexPointA(int indexPointA) {
		this.indexPointA = indexPointA;
	}

	public int getIndexPointB() {
		return indexPointB;
	}

	public void setIndexPointB(int indexPointB) {
		this.indexPointB = indexPointB;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

    /**
     *
     * @param l
     */
    public abstract void setL(Line2D.Double l); 

    /**
     *
     * @param l
     */
    public abstract void setL(Shape l);

    /**
     *
     * @param p1
     * @param p2
     */
    public abstract Shape3D getShape();
    public abstract Text2D getText();

    /**
     *
     */
    public abstract void creatLine(BranchGroup branchGroup);
}
