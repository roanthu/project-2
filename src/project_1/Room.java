/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingBox;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Matrix3d;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import static project_1.Nut.getAppearance;

/**
 *
 * @author Roan Thu
 */
public class Room implements Serializable{
    BufferedImage image;
    Image image2;
    Point point = new Point();
    Box box;
    public TransformGroup group = new TransformGroup();
    private Shape3D frontShape;
    private BufferedImage frontImage;
    private int imageHeight = 500;
    private int imageWidth = 500;
    private Appearance appearance;
    private Texture texture;
    private TextureLoader loader;
    private float xBox,yBox,zBox;
    private float OPhong;
    private ArrayList<TransformGroup> arrTransObj = new ArrayList<>();
    private  ArrayList<Boolean> Xoay = new ArrayList<>();
    private  ArrayList<Integer> ThuTu = new ArrayList<>();
    private boolean Rorate = false;

    public ArrayList<Integer> getThuTu() {
        return ThuTu;
    }

    public void setThuTu(ArrayList<Integer> ThuTu) {
        this.ThuTu = ThuTu;
    }
    

    public ArrayList<Boolean> getXoay() {
        return Xoay;
    }

    public void setXoay(ArrayList<Boolean> Xoay) {
        this.Xoay = Xoay;
    }
    

    public boolean isRorate() {
        return Rorate;
    }

    public void setRorate(boolean Rorate) {
        this.Rorate = Rorate;
    }
    public ArrayList<TransformGroup> getArrTransObj() {
        return arrTransObj;
    }

    public void setArrTransObj(ArrayList<TransformGroup> arrTransObj) {
        this.arrTransObj = arrTransObj;
    }

    public float getOPhong() {
        return OPhong;
    }

    public void setOPhong(float OPhong) {
        this.OPhong = OPhong;
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
    //3d node
    private Transform3D trans = new Transform3D();

    public void createRoom(double wight, double height,float O,BranchGroup BranhGroup){
        trans.setTranslation(new Vector3d(0, 0, -0.08f));
        group = new TransformGroup(trans);
               xBox = (float) wight;yBox = (float) height;
        this.OPhong = O;
	box = new Box((float) wight, (float) height, 0.01f, Primitive.GENERATE_TEXTURE_COORDS,
				getAppearance(new Color3f(Color.red)));		 
	box.setCapability(Box.ENABLE_APPEARANCE_MODIFY);
	box.setCapability(Box.GEOMETRY_NOT_SHARED);		
	box.setCapability(Box.ALLOW_LOCAL_TO_VWORLD_READ);
	frontShape = box.getShape(Box.FRONT);
	//frontShape.setAppearance(ap);	 
	
	imageWidth = (int) (500*wight);
        imageHeight = (int) (500*height);
        int OPhong = (int) (500*O);
	frontImage = new BufferedImage(imageWidth, imageHeight,
			BufferedImage.TYPE_INT_RGB);


        //ve o da hoa
	Graphics2D g = (Graphics2D)frontImage.getGraphics();
	g.setColor(Color.gray);
	g.fillRect(0, 0, imageWidth, imageHeight);
        g.setColor(Color.green);
        int ONgang = (int) (imageHeight/OPhong);
        int ODoc = (int) (imageWidth/OPhong);
        for(int i = 0; i < ODoc+1; i++){
            g.drawLine(i*OPhong,0, i*OPhong, imageHeight);
            
        }
        for(int j=0;j < ONgang+1; j++){
                g.drawLine(0,j*OPhong, imageWidth, j*OPhong);
            }
        addTexture(frontImage, frontShape);
        //create wall
        Appearance ap = getAppearance("resources/Wall.jpg");
        Box wall1 = new Box(0.01f, yBox, 0.2f, Primitive.GENERATE_TEXTURE_COORDS,
				getAppearance(new Color3f(Color.GRAY)));
        wall1.setCapability(Box.ENABLE_APPEARANCE_MODIFY);
        wall1.getShape(Box.TOP).setAppearance(ap);
	wall1.getShape(Box.BOTTOM).setAppearance(ap); ;
	wall1.getShape(Box.RIGHT).setAppearance(ap);
	wall1.getShape(Box.LEFT).setAppearance(ap); 
	wall1.getShape(Box.BACK).setAppearance(ap) ;
        trans.setTranslation(new Vector3d(-xBox, 0.0f, 0.2-0.07));
        TransformGroup groupwall1 = new TransformGroup(trans);
        groupwall1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        groupwall1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        groupwall1.addChild(wall1);
        //create wall2
        Box wall2 = new Box(xBox, 0.01f, 0.2f, Primitive.GENERATE_TEXTURE_COORDS,
				getAppearance(new Color3f(Color.GRAY)));
        wall2.setCapability(Box.ENABLE_APPEARANCE_MODIFY);
        wall2.getShape(Box.TOP).setAppearance(ap);
	wall2.getShape(Box.BOTTOM).setAppearance(ap); ;
	wall2.getShape(Box.RIGHT).setAppearance(ap);
	wall2.getShape(Box.LEFT).setAppearance(ap); 
	wall2.getShape(Box.BACK).setAppearance(ap) ;
        trans.setTranslation(new Vector3d(0.0f, yBox, 0.2-0.07));
        TransformGroup groupwall2 = new TransformGroup(trans);
        groupwall2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        groupwall2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        groupwall2.addChild(wall2);
	group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);	
	group.addChild(box);
        BranchGroup tempBranch = new BranchGroup();
        tempBranch.setCapability(BranchGroup.ALLOW_DETACH);
        tempBranch.addChild(group);
        tempBranch.addChild(groupwall1);
        tempBranch.addChild(groupwall2);
	BranhGroup.addChild(tempBranch);
        return;
    }
    //tao may lanh
    public void createCrac(double x, double y,float xCrac,float yCrac, float zCrac,BranchGroup BranhGroup){
        Transform3D trans = new Transform3D();
        double z = -0.07 +zCrac;
        ThuTu.add(1);
        if(Rorate == true){
            trans.rotZ(Math.PI/2);
            Xoay.add(true);
        } 
        else Xoay.add(false);
        trans.setTranslation(new Vector3d(x,y,z));
        TransformGroup group = new TransformGroup(trans);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        group.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);
        Appearance ap = getAppearance("resources/CR.jpg");
        ap.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
	ap.setCapability(Appearance.ALLOW_TEXGEN_WRITE);
        Box Crac = new Box(xCrac, yCrac, zCrac, Primitive.GENERATE_TEXTURE_COORDS,
				getAppearance(new Color3f(Color.blue)));
        Crac.setCapability(Box.ENABLE_APPEARANCE_MODIFY);
	Crac.setCapability(Box.GEOMETRY_NOT_SHARED);		
	Crac.setCapability(Box.ALLOW_LOCAL_TO_VWORLD_READ);
        Crac.getShape(Box.TOP).setAppearance(ap);
	Crac.getShape(Box.BOTTOM).setAppearance(ap); ;
	Crac.getShape(Box.RIGHT).setAppearance(ap);
	Crac.getShape(Box.LEFT).setAppearance(ap); 
	Crac.getShape(Box.BACK).setAppearance(ap) ;
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);	
	group.addChild(Crac);
        arrTransObj.add(group);
        BranchGroup tempBranch = new BranchGroup();
        tempBranch.setCapability(BranchGroup.ALLOW_DETACH);
        tempBranch.addChild(group);
	BranhGroup.addChild(tempBranch);
    }
    //may phat dien
    public void createPDU(double x, double y,float xPDU,float yPDU, float zPDU,BranchGroup BranhGroup){
        Transform3D trans = new Transform3D();
        double z = -0.07 +zPDU;
        ThuTu.add(2);
        if(Rorate == true){
            trans.rotZ(Math.PI/2);
            Xoay.add(true);
        } 
        else Xoay.add(false);
        trans.setTranslation(new Vector3d(x,y,z));
        TransformGroup group= new TransformGroup(trans);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        group.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);
        Appearance ap = getAppearance("resources/PDU.jpg");
        ap.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
	ap.setCapability(Appearance.ALLOW_TEXGEN_WRITE);
        Box PDU = new Box(xPDU, yPDU, zPDU, Primitive.GENERATE_TEXTURE_COORDS,
				getAppearance(new Color3f(Color.red)));
        PDU.setCapability(Box.ENABLE_APPEARANCE_MODIFY);
	PDU.setCapability(Box.GEOMETRY_NOT_SHARED);		
	PDU.setCapability(Box.ALLOW_LOCAL_TO_VWORLD_READ);
        PDU.getShape(Box.TOP).setAppearance(ap);
	PDU.getShape(Box.BOTTOM).setAppearance(ap); ;
	PDU.getShape(Box.RIGHT).setAppearance(ap);
	PDU.getShape(Box.LEFT).setAppearance(ap); 
	PDU.getShape(Box.BACK).setAppearance(ap) ;
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);	
	group.addChild(PDU);
        arrTransObj.add(group);
        BranchGroup tempBranch = new BranchGroup();
        tempBranch.setCapability(BranchGroup.ALLOW_DETACH);
        tempBranch.addChild(group);
	BranhGroup.addChild(tempBranch);
    }
    //thoat khi
    public void createGrill(double x, double y,BranchGroup BranhGroup){
         Transform3D trans = new Transform3D();
         ThuTu.add(3);
        if(Rorate == true){
            trans.rotZ(Math.PI/2);
            Xoay.add(true);
        } 
        else Xoay.add(false);
        trans.setTranslation(new Vector3d(x,y,0.125*2-0.05f));
        TransformGroup group= new TransformGroup(trans);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        group.setCapability(TransformGroup.ALLOW_LOCAL_TO_VWORLD_READ);
        Appearance ap = getAppearance("resources/Grill.jpg");
        ap.setCapability(Appearance.ALLOW_TEXTURE_WRITE);
	ap.setCapability(Appearance.ALLOW_TEXGEN_WRITE);
        Box Grill = new Box(OPhong, 2*OPhong, 0.002f, Primitive.GENERATE_TEXTURE_COORDS,
				getAppearance(new Color3f(Color.MAGENTA)));
        Grill.setCapability(Box.ENABLE_APPEARANCE_MODIFY);
	Grill.setCapability(Box.GEOMETRY_NOT_SHARED);		
	Grill.setCapability(Box.ALLOW_LOCAL_TO_VWORLD_READ);
        Grill.getShape(Box.FRONT).setAppearance(ap);
	Grill.getShape(Box.BACK).setAppearance(ap) ;
        group.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);	
	group.addChild(Grill);
        arrTransObj.add(group);
        BranchGroup tempBranch = new BranchGroup();
        tempBranch.setCapability(BranchGroup.ALLOW_DETACH);
        tempBranch.addChild(group);
	BranhGroup.addChild(tempBranch);
    }
    public void setPosition(int index,double x,double  y){
        Transform3D trans = new Transform3D();
        arrTransObj.get(index).getTransform(trans);
        Vector3d vector = new Vector3d();
        trans.get(vector);
        trans.setTranslation(new Vector3d(x, y,vector.z));
        arrTransObj.get(index).setTransform(trans);
    }
    public void rorate(int index){
        if(Xoay.get(index) == false) {
            Rorate = true;
            Xoay.set(index, true);
            Transform3D trans = new Transform3D();
            arrTransObj.get(index).getTransform(trans);
            Vector3d vector = new Vector3d();
            trans.get(vector);
            trans.rotZ(Math.PI/2);
            trans.setTranslation(vector);
            arrTransObj.get(index).setTransform(trans);
        }
        else {
            Rorate = false;
            Xoay.set(index, false);
            Transform3D trans = new Transform3D();
            arrTransObj.get(index).getTransform(trans);
            Vector3d vector = new Vector3d();
            trans.get(vector);
            trans.rotZ(0);
            trans.setTranslation(vector);
            arrTransObj.get(index).setTransform(trans);
        }
        
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
		// Set up the texture attributes
		// could be REPLACE, BLEND or DECAL instead of MODULATE
		
		// front = getAppearance(new Color3f(Color.YELLOW));
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
    public static Appearance getAppearance(String path){
        Appearance ap = new Appearance();
        TextureLoader loader = new TextureLoader(path, new Container());
        Texture texture = loader.getTexture();
        TextureAttributes texAttr = new TextureAttributes();
		texAttr.setTextureMode(TextureAttributes.MODULATE);
		texture.setBoundaryModeS(Texture.WRAP);
		texture.setBoundaryModeT(Texture.WRAP);
                ap.setTextureAttributes(texAttr);
		ap.setTexture(texture);
        return ap;
    }

    public Room() {
    }
}
