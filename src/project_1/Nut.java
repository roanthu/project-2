/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Point3D;
import javax.imageio.ImageIO;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingBox;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GraphicsContext3D;
import javax.media.j3d.LineArray;
import javax.media.j3d.Material;
import javax.media.j3d.PositionInterpolator;
import javax.media.j3d.Shader;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author Roan Thu
 */
public class Nut implements Serializable{
    private Point3d p;
    private String index;
    private Color colorPoint;
    BufferedImage image;
    Image image2;
    Point point = new Point();
    private Box box = new Box();
    private Box box1 = new Box();
    private BranchGroup tempBranch = new BranchGroup();
    public TransformGroup group = new TransformGroup();
    private Shape3D frontShape;
    private BufferedImage frontImage;
    private int imageHeight = 100;
    private int imageWidth = 100;
    private Appearance appearance;
    private Texture texture;
    private TextureLoader loader;
    private double x,y,z;
    private float xBox,yBox,zBox;
    public static boolean RorateStatic = false;
    private boolean Xoay = false;
    private boolean edge = true;
    private int freeport = 0;
    private int server = 8, port;

    public int getServer() {
        return server;
    }

    public void setServer(int server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getFreeport() {
        return freeport;
    }

    public void setFreeport(int freeport) {
        this.freeport = freeport;
    }
    

    public BranchGroup getTempBranch() {
        return tempBranch;
    }

    public void setTempBranch(BranchGroup tempBranch) {
        this.tempBranch = tempBranch;
    }

    public boolean isEdge() {
        return edge;
    }

    public void setEdge(boolean edge) {
        this.edge = edge;
        this.server = 0;
    }
    

    public boolean isXoay() {
        return Xoay;
    }

    public void setXoay(boolean Xoay) {
        this.Xoay = Xoay;
    }

    

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getxBox() {
        return xBox;
    }

    public void setxBox(float xBox) {
        this.xBox = xBox;
    }

    public float getyBox() {
        return yBox;
    }

    public void setyBox(float yBox) {
        this.yBox = yBox;
    }

    public float getzBox() {
        return zBox;
    }

    public void setzBox(float zBox) {
        this.zBox = zBox;
    }
    
    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public Box getBox1() {
        return box1;
    }

    public void setBox1(Box box1) {
        this.box1 = box1;
    }
    
    //3d node
    private Transform3D trans = new Transform3D();
    public Color getColorPoint() {
        return colorPoint;
    }

    public void setColorPoint(Color colorPoint) {
        this.colorPoint = colorPoint;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
    private float r;

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }
    private String A; 
    private String B;
    private String C;
    private String D;

    public String getA() {
        return A;
    }

    public void setA(String A) {
        this.A = A;
    }

    public String getB() {
        return B;
    }

    public void setB(String B) {
        this.B = B;
    }

    public String getC() {
        return C;
    }

    public void setC(String C) {
        this.C = C;
    }

    public String getD() {
        return D;
    }

    public void setD(String D) {
        this.D = D;
    }
    public void setPosition(double x, double y){
       Transform3D trans = new Transform3D();
        group.getTransform(trans);
        Vector3d vector = new Vector3d();
        trans.get(vector);
        trans.setTranslation(new Vector3d(x,y,vector.z));
        group.setTransform(trans);
        this.p = new Point3d(x+xBox-xBox/8, y-yBox, p.z);
    }
    public void rorate(){
        if(Xoay == false) {
            Xoay = true;
            RorateStatic = true;
            Transform3D trans = new Transform3D();
            group.getTransform(trans);
            Vector3d vector = new Vector3d();
            trans.get(vector);
            trans.rotZ(Math.PI/2);
            trans.setTranslation(vector);
            group.setTransform(trans);
        }
        else {
            Xoay = false;
            RorateStatic = false;
            Transform3D trans = new Transform3D();
            group.getTransform(trans);
            Vector3d vector = new Vector3d();
            trans.get(vector);
            trans.rotZ(0);
            trans.setTranslation(vector);
            group.setTransform(trans);
        }
    }
    private Point3f sizeBox;

    public Point3f getSizeBox() {
        return sizeBox;
    }

    public void setSizeBox(Point3f sizeBox) {
        this.sizeBox = sizeBox;
    }
    
    public void createNode(BranchGroup branhgroup){
        this.sizeBox = new Point3f(xBox, yBox, zBox);
        if(RorateStatic==true){
            trans.rotZ(Math.PI/2);
            Xoay = true;
        }
        trans.setTranslation(new Vector3d(x, y, z));
        group = new TransformGroup(trans);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        group.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Appearance ap = getAppearance();
        ap.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
	ap.setCapability(Appearance.ALLOW_TEXGEN_WRITE);
       

        //create switch
        Box Switch = new Box(xBox, yBox, zBox/8, Primitive.GENERATE_TEXTURE_COORDS,
				getAppearance(new Color3f(Color.LIGHT_GRAY)));
        trans = new Transform3D();
        trans.setTranslation(new Vector3d(0, 0, zBox +zBox/8));
        TransformGroup tempGroup = new TransformGroup(trans);
        tempGroup.addChild(Switch);
        this.box = Switch;
        frontShape = Switch.getShape(Box.FRONT);
	frontShape.setAppearance(ap);
        frontImage = new BufferedImage(imageWidth, imageHeight,
			BufferedImage.TYPE_INT_RGB);
	Graphics2D g = (Graphics2D)frontImage.getGraphics();
	g.setColor(Color.DARK_GRAY);
	g.fillRect(0, 0, 200, 200);
        g.setColor(Color.RED);
        Font f = new Font("Aria", Font.BOLD, 50);
        g.setFont(f);
        int stringLen = (int) g.getFontMetrics()
				.getStringBounds(String.valueOf(index), g).getWidth();
	int stringHeight = (int) g.getFontMetrics()
				.getStringBounds(String.valueOf(index), g).getHeight();
	int startX = -stringLen / 2;
	int startY = stringHeight / 2;
        
        g.drawString(index, 50 + startX,45+startY);
        addTexture(frontImage, frontShape);
        group.addChild(tempGroup);
         if(edge == true){         //create rack
	Box rack = new Box(xBox, yBox, zBox, Primitive.GENERATE_TEXTURE_COORDS,
				getAppearance(new Color3f(Color.green)));		 
	rack.setCapability(Box.ENABLE_APPEARANCE_MODIFY);
	rack.setCapability(Box.GEOMETRY_NOT_SHARED);		
	rack.setCapability(Box.ALLOW_LOCAL_TO_VWORLD_READ);
	//frontShape = rack.getShape(Box.FRONT);
	//frontShape.setAppearance(ap);	 
	//rack.getShape(Box.TOP).setAppearance(getAppearance());
	//rack.getShape(Box.BOTTOM).setAppearance(getAppearance()); ;
	//rack.getShape(Box.RIGHT).setAppearance(getAppearance());
	//rack.getShape(Box.LEFT).setAppearance(getAppearance()); 
	//rack.getShape(Box.BACK).setAppearance(getAppearance()) ;
        this.box1 = rack;
        group.addChild(rack);
        drawserver(rack.getShape(Box.LEFT),false);
        drawserver(rack.getShape(Box.RIGHT),false);
        drawserver(rack.getShape(Box.TOP),true);
        drawserver(rack.getShape(Box.BOTTOM),true);
        
        for(int i = 1;i <= server; i++){
            LineArray l = new LineArray(6, LineArray.COORDINATES|LineArray.COLOR_3);
            double d = 2*zBox/server;
            double dx = 2*xBox/(server+1);
            l.setCoordinate(0, new Point3d(p.x-x-dx*i, p.y-y, p.z-z));l.setColor(0, new Color3f(Color.WHITE));
            l.setCoordinate(1, new Point3d(p.x-x-dx*i, p.y-0.015-y, p.z-z));l.setColor(1, new Color3f(Color.WHITE));
            l.setCoordinate(2, new Point3d(p.x-x-dx*i, p.y-0.015-y, p.z-z));l.setColor(2, new Color3f(Color.WHITE));
            l.setCoordinate(3, new Point3d(p.x-x-dx*i, p.y-0.015-y, p.z-d*i-z));l.setColor(3, new Color3f(Color.WHITE));
            l.setCoordinate(4, new Point3d(p.x-x-dx*i, p.y-0.015-y, p.z-d*i-z));l.setColor(4, new Color3f(Color.WHITE));
            l.setCoordinate(5, new Point3d(p.x-x-dx*i, p.y-y, p.z-d*i-z));l.setColor(5, new Color3f(Color.WHITE));
            Shape3D shape = new Shape3D(l);
            group.addChild(shape);
        }
        
        
        }

        tempBranch.setCapability(BranchGroup.ALLOW_DETACH);
        tempBranch.addChild(group);
	branhgroup.addChild(tempBranch);
        return;
    }
    public void drawserver(Shape3D shape, boolean rorate){
        Appearance ap = getAppearance();
        ap.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
	ap.setCapability(Appearance.ALLOW_TEXGEN_WRITE);
        shape.setAppearance(ap);
        BufferedImage image = new BufferedImage(imageWidth, imageHeight,
			BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D)image.getGraphics();
        if(server==0) return;
        float d = imageHeight/(server);
        g.setColor(Color.RED);
        g.drawLine(0, 0, imageWidth,0 );
        g.drawLine(0, imageHeight, imageWidth,imageHeight);
        g.drawLine(0, 0, 0, imageHeight);
        g.drawLine(imageWidth, 0, imageWidth, imageWidth);
        if(rorate == true)
            for(int i = 0; i <= server ; i++){
                g.drawLine(0, (int)d*i, 200, (int)d*i);
            }
        else 
            for(int i = 0; i <= server ; i++){
                g.drawLine((int)d*i, 0, (int)d*i,200);
        }
        addTexture(image, shape);
    }
    public void addTexture(BufferedImage image, Shape3D shape) {
		frontShape.setCapability(Shape3D.ALLOW_APPEARANCE_WRITE);
		appearance = shape.getAppearance();
		appearance.setCapability(Appearance.ALLOW_TEXTURE_ATTRIBUTES_WRITE);
		appearance.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
		appearance.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
		changeTexture( texture,  image,  shape);		
		Color3f col = new Color3f(0.0f, 0.0f, 1.0f);
		ColoringAttributes ca = new ColoringAttributes(col,
				ColoringAttributes.NICEST);
		appearance.setColoringAttributes(ca);
		
		}
     public void changeTexture(Texture texture, BufferedImage image, Shape3D shape) {
    	loader = new TextureLoader(image, "resources/sc.jpg",
				TextureLoader.ALLOW_NON_POWER_OF_TWO);
    	texture = loader.getTexture();
		texture.setBoundaryModeS(Texture.CLAMP_TO_BOUNDARY);
		texture.setBoundaryModeT(Texture.CLAMP_TO_BOUNDARY);
		texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.5f, 0f));
		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f white = new Color3f(1.0f, 1.0f,1.0f);
                Color3f red = new Color3f(0.7f, 0.15f,0.15f);
                //appearance.setMaterial(new Material(red, black, red, white, 1.0f));
                TextureAttributes texAttr = new TextureAttributes();
                texAttr.setTextureMode(TextureAttributes.REPLACE);
                appearance.setTextureAttributes(texAttr);
                appearance.setTexture(texture);
		shape.setAppearance(appearance);
	}
    public static Appearance getAppearance(Color color) {
		return getAppearance(new Color3f(color));
	}
    public static Appearance getAppearance(Color3f color) {
		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
		Appearance ap = new Appearance();
		Texture texture = new Texture2D();
		TextureAttributes texAttr = new TextureAttributes();
		texAttr.setTextureMode(TextureAttributes.MODULATE);
		texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);
		texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
		Material mat = new Material(color, black, color, white, 70f);
		ap.setTextureAttributes(texAttr);
		ap.setMaterial(mat);
		ap.setTexture(texture);	 
		ColoringAttributes ca = new ColoringAttributes(color,
				ColoringAttributes.NICEST);
		ap.setColoringAttributes(ca);
		return ap;
	
    }
    public static Appearance getAppearance(){
        Appearance ap = new Appearance();
        TextureLoader loader = new TextureLoader("resources/sc.jpg", new Container());
        
        Texture texture = loader.getTexture();
        TextureAttributes texAttr = new TextureAttributes();
		texAttr.setTextureMode(TextureAttributes.MODULATE);
		texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);
                ap.setTextureAttributes(texAttr);
		ap.setTexture(texture);
        return ap;
    }
    
    public Point3d getP() {
	return p;
    }

    public void setP(Point3d p) {
	this.p = p;
    }
    public Nut(double x,double y,String index, String A, String B, String C, String D,float xBox,float yBox,float zBox) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.index = index;
        //3d node
        this.x = x;
        this.y = y;
        this.xBox = xBox;
        this.yBox = yBox;
        this.zBox = zBox;
        this.p = new Point3d(x+xBox-xBox/8, y-yBox, 2*zBox+zBox/8-0.07);
        this.z = -0.07+zBox;
    }
        
        public Nut(double x,double y,double z,String index, String A, String B, String C, String D,float xBox,float yBox,float zBox) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.index = index;
        //3d node
        this.x = x;
        this.y = y;
        this.z = z;
        this.xBox = xBox;
        this.yBox = yBox;
        this.zBox = zBox;
        this.p = new Point3d(x+xBox-xBox/8, y-yBox,z + 2*zBox+zBox/8-0.07);
    }
           

    public Nut(double x,double y,String index, String A, String B, String C, String D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.index = index;
        //3d node
        this.x = x;
        this.y = y;
        this.xBox = 0.05f;
        this.yBox = 0.05f;
        this.zBox = 0.07f;
        this.z = 0;
        this.p = new Point3d(x+xBox-xBox/8, y-yBox, 2*zBox+zBox/8-0.07);
    }
     public Nut(double x,double y,double z,String index, String A, String B, String C, String D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.index = index;
        //3d node
        this.x = x;
        this.y = y;
        this.z = z;
        this.xBox = 0.05f;
        this.yBox = 0.05f;
        this.zBox = 0.07f;
        this.p = new Point3d(x+xBox-xBox/8, y-yBox, z+2*zBox+zBox/8-0.07);
    }
}
