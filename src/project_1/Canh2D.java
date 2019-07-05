/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import com.sun.j3d.utils.geometry.Text2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.io.Serializable;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.LineArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 *
 * @author Roan Thu
 */
public class Canh2D extends Canh implements Serializable{

    @Override
    public void setL(Shape l) {
    }

    @Override
    public void drawLine(Graphics2D g, Point p1, Point p2, int size) {
        
    }

    @Override
    public boolean containerPoint(Point p) {
        return false;
    } 
    ////3ddd
    private LineArray axisXLines;
    private Shape3D shape;
    private Text2D text;
    @Override
    public void setL(Line2D.Double l) {
    }

    @Override
    public Shape3D getShape() {
        return shape;
    }

    @Override
    public Text2D getText() {
        return text;
    }
    @Override
    public void creatLine(BranchGroup branchGroup){
        if(super.isIsHide()==false){
        Color3f cor = new Color3f(super.getColorLine());
        Point3d p1 = super.p1;
        Point3d p2 = super.p2;
        double a = Math.sqrt(Math.pow(p2.y - p1.y,2)+Math.pow( p2.x - p1.x, 2))/2;
        double b = 0.4*a;
        double x1 = a;
        double y1 = 0;
        double x2 = 0,y2 = 0.01;
        Transform3D trans = new Transform3D();
        int n = (int) (b/0.01);
        LineArray l = new LineArray(n*4+8, LineArray.COORDINATES|LineArray.COLOR_3);
        int i = 0;
        while(y2<b){
            l.setCoordinate(i, new Point3d(-x1, y1, p1.z));
            x2 = a*Math.sqrt(1 - Math.pow(y2/b, 2));
            l.setCoordinate(i+1, new Point3d(-x2, y2, p2.z));
            l.setCoordinate(i+2, new Point3d(x1, y1, p1.z));
            l.setCoordinate(i+3, new Point3d(x2, y2, p2.z));
            if(y2<b){
                y1=y2;x1=x2;
            }
            y2+=0.01;
            i = i + 4;
        }    
        l.setCoordinate(i, new Point3d(-x1, y1, p1.z));
        l.setCoordinate(i+1, new Point3d(0, b, p2.z));
        l.setCoordinate(i+2, new Point3d(x1, y1, p1.z));
        l.setCoordinate(i+3, new Point3d(0, b, p2.z));
        for(int j = 0; j < n*4 + 4; j++){
           l.setColor(j, cor);
        }
        Shape3D shape = new Shape3D(l);
        double theta, tan;
        if(p2.x != p1.x) {
            tan = (p2.y-p1.y)/(p2.x-p1.x);
            theta = Math.atan(tan);
        }
        else {
            if (p2.y < p1.y) theta = -Math.PI/2 ;
            else theta = Math.PI/2;
        }
        if(p2.x<p1.x) theta = theta + Math.PI;
        trans.rotZ(theta);
        trans.setTranslation(new Vector3d((p2.x+p1.x)/2, (p2.y+p1.y)/2, 0));
        TransformGroup objTrans = new TransformGroup(trans);
        objTrans.addChild(shape);
        this.shape = shape;
        Text2D string = new Text2D(String.valueOf(super.getCost()),new Color3f(cor),"Helvetica", 10, Font.ITALIC);
        this.text = string;
        Transform3D temp = new Transform3D();
        trans = new Transform3D();
        trans.setTranslation(new Vector3d((p1.x + p2.x)/2 - b*Math.sin(theta) ,  (p1.y + p2.y)/2 + b*Math.cos(theta), (p2.z+p1.z)/2));
        TransformGroup objTrans1 =  new TransformGroup(trans);
        objTrans1.addChild(string);
        this.axisXLines = l;
        super.getTempBranch().addChild(objTrans);
        super.getTempBranch().addChild(objTrans1);
        branchGroup.addChild(super.getTempBranch());
    }
    }
    public Canh2D(Point3d p1, Point3d p2 ,int indexPointA,int indexPointB,int cost,Color colorLine) {
            super(p1,p2,indexPointA, indexPointB, cost,colorLine);
            //shape = creatline2D(p1, p2,cost,colorLine);
	}
}
