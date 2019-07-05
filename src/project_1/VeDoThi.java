/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Box;

//import com.sun.j3d.utils.behaviors.picking.PickTranslateBehavior;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Text2D;
import com.sun.j3d.utils.pickfast.behaviors.PickRotateBehavior;
import com.sun.j3d.utils.pickfast.behaviors.PickTranslateBehavior;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingBox;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.GraphicsContext3D;
import javax.media.j3d.Group;
import javax.media.j3d.LineArray;
import javax.media.j3d.Locale;
import javax.media.j3d.Material;
import javax.media.j3d.Node;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.TransferHandler;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import static project_1.GiaoDienChinh.graph;
import static project_1.Nut.getAppearance;

/**
 *
 * @author Roan Thu
 */
public class VeDoThi extends Applet implements MouseListener, MouseMotionListener,ActionListener,Serializable{
    public Data data, data2;
    private int x = 0, y = 0, r = 15, r2 = 2 * r; // ban kinh, duong kinh
    private int indexPointBeginLine=-1, indexPointEndLine, indexTemp, indexPoint, indexLine,indexObjRoom=-1;
    private Box pointBeginLine;
    private Point point, beginpointdrag;
    private Color colorBackGround = Color.white, colorCost = Color.white,
			colorIndex = Color.white, colorDraw = Color.white;
    private int sizeLine = 1;
    boolean isFindPoint = true;
    float size = 5000f;
    boolean addRack = false, addCrac = false, addPDU = false,addGrill=false;
    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        
    Canvas3D canvas = new Canvas3D(config);
    
    transient Background background = new Background();
    
    SimpleUniverse universe = new SimpleUniverse(canvas);
    transient BranchGroup mainbranch = new BranchGroup();
    transient BranchGroup group = new BranchGroup();
    transient BranchGroup group1 = new BranchGroup();
    transient BranchGroup groupRoom = new BranchGroup();
    transient BranchGroup objRoom = new BranchGroup();
    TransformGroup objtrans = new TransformGroup();
    Transform3D trans = new Transform3D();
    Transform3D tempRotate = new Transform3D();
    PickCanvas pickcanvas;
    PickTranslateBehavior pickTranslate ;
    PickRotateBehavior pickRotate;
    Point3d pointintersection;
    ArrayList<Canh> temparr;
    Point3d eyePos = new Point3d();
    Point3d mousePos = new Point3d();
    transient MouseRotate behavor = new MouseRotate();
    Room room = new Room();
    public float xRack,yRack,zRack;
    public float xCrac,yCrac,zCrac;
    public float xPDU,yPDU,zPDU;
    public TransformGroup transGroup = new TransformGroup();
    int flagview = 0;
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public  void init(){
        transGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transGroup.setCapability(TransformGroup.ALLOW_BOUNDS_READ);
        transGroup.setCapability(TransformGroup.ALLOW_BOUNDS_WRITE);
        transGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        transGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        group.setCapability(BranchGroup.ALLOW_DETACH);
        group.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        group.setCapability(Group.ALLOW_CHILDREN_WRITE);
        group1.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        group1.setCapability(BranchGroup.ALLOW_DETACH);
        group1.setCapability(Group.ALLOW_CHILDREN_WRITE);
        groupRoom.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        groupRoom.setCapability(BranchGroup.ALLOW_DETACH);
        groupRoom.setCapability(Group.ALLOW_CHILDREN_WRITE);
        objRoom.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        objRoom.setCapability(BranchGroup.ALLOW_DETACH);
        objRoom.setCapability(Group.ALLOW_CHILDREN_WRITE);
        transGroup.addChild(group);
        transGroup.addChild(group1);
        transGroup.addChild(groupRoom);
        transGroup.addChild(objRoom);
        Line.group  = objtrans;
        objtrans.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        objtrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        canvas.setSize(1220,700);
        BoundingSphere behaveBounds = new BoundingSphere();
        objtrans.setCapability(TransformGroup.ALLOW_BOUNDS_READ);
        objtrans.setCapability(TransformGroup.ALLOW_BOUNDS_WRITE);
        behavor.setSchedulingBounds(behaveBounds);
        //pickRotate = new PickRotateBehavior(group, canvas, behaveBounds);
        pickTranslate = new PickTranslateBehavior(group, canvas, behaveBounds,1);
        background.setColor(new Color3f(Color.DARK_GRAY));
        background.setApplicationBounds(new BoundingSphere());
        mainbranch.addChild(background);
        //group.addChild(pickTranslate);
        group.addChild(objtrans);
        //group.addChild(pickRotate);
        universe.getViewingPlatform().setNominalViewingTransform();
        mainbranch.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        mainbranch.setCapability(Group.ALLOW_CHILDREN_WRITE);
        mainbranch.setCapability(BranchGroup.ALLOW_BOUNDS_WRITE);
        mainbranch.addChild(transGroup);
        //rorate
        BoundingBox bounds =

      new BoundingBox(new Point3d(0.0,0.0,0.0), new Point3d(2,5,7));
        //behavor.setTransformGroup(transGroup);
        //
        transGroup.addChild(behavor);
        behavor.setTransformGroup(transGroup);
        behavor.setSchedulingBounds(bounds);
        behavor.setEnable(false);
        //
        xRack=0.05f;yRack=0.05f;zRack=0.07f;
        xCrac=(float) (1.5/40);yCrac=(float)3/40;zCrac=(float)4/40;
        xPDU=(float) (1.5/40);yPDU=(float) (2.5/40);zPDU=(float)3/40;
        canvas.addMouseMotionListener(this);
	canvas.addMouseListener(this);
    }
    public VeDoThi(int SoDinh, int SoCanh) {
        init();
        data = new Data(SoDinh, SoCanh);
        redraw();
        temparr = arrtemp();
        if(SoDinh!=0) {
            room.createRoom(1.2, 1.5, 0.05f, groupRoom);
        }
        universe.addBranchGraph(mainbranch);

    } 
    
    public VeDoThi(Data data){
        init();
        this.data = data;
        redraw();
        temparr = arrtemp();
        universe.addBranchGraph(mainbranch);
    }
    public VeDoThi(int m, int n, boolean Torus){//2d 2d torus mesh
        init();
        data = new Data(m, n, Torus);
        room.createRoom(n*0.1, m*0.1, 0.05f, groupRoom);
        redraw();
        temparr = arrtemp();
        universe.addBranchGraph(mainbranch);
    }
    public VeDoThi(int n, double x , double y){ //3 4 5 6d cube
        init();
        data = new Data(n, x, y, 0);
        room.createRoom(n*0.4, n*0.3, 0.05f, groupRoom);
        redraw();
        temparr = arrtemp();
        universe.addBranchGraph(mainbranch);
    }
    public VeDoThi( double wight,double height,double d, float xBox, float yBox, float zBox){ // lay out
        init();
        data = new Data(wight, height, d, xBox, yBox, zBox);
        room.createRoom(wight/2,height/2, 0.05f, groupRoom);
        redraw();
        temparr = arrtemp();
        universe.addBranchGraph(mainbranch);   
    }
    public VeDoThi(int m, int n, int span){// small world
        init();
        data = new Data(m, n, span);
        room.createRoom(n*0.1,m*0.1, 0.05f, groupRoom);
        redraw();
        temparr = arrtemp();
        universe.addBranchGraph(mainbranch);

    }
    public VeDoThi(int port){// fat tree
        init();
        if(port%2==1){
            data = new Data(0, 0);
            JOptionPane.showMessageDialog(null, "Số bậc không hợp lệ");
        }
        else data = new Data(port);
        room.createRoom(port*0.15,port*0.15/2, 0.05f, groupRoom);
        redraw();
        temparr = arrtemp();
        universe.addBranchGraph(mainbranch);

    }
    public VeDoThi(int sever,int Switch, int port, boolean jellyfish){
        init();
        data = new Data(sever,Switch, port,  jellyfish);
        int N;
        if(Switch < sever) N = Switch;
        else N = sever;
        room.createRoom(Math.sqrt(N)*0.15+0.06,Math.sqrt(N)*0.15+0.05, 0.05f, groupRoom);
        redraw();
        temparr = arrtemp();
        universe.addBranchGraph(mainbranch);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==1){
                    if(addPDU == true){
            Point3d point = getPosition(e);
            point.x = point.x-xPDU;
            point.y = point.y+yPDU;
            int indexX = (int) ((point.x+room.getxBox())/room.getOPhong());
            int indexY=(int) ((room.getyBox()-point.y)/room.getOPhong());
            double x = -room.getxBox()+indexX*room.getOPhong()+xPDU;
            double y =room.getyBox()-indexY*room.getOPhong()-yPDU;
            room.createPDU(x, y, xPDU, yPDU, zPDU, objRoom);
            return;
        }
        if(addCrac ==true){
            Point3d point = getPosition(e);
            point.x = point.x-xCrac;
            point.y = point.y+yCrac;
            int indexX = (int) ((point.x+room.getxBox())/room.getOPhong());
            int indexY=(int) ((room.getyBox()-point.y)/room.getOPhong());
            double x = -room.getxBox()+indexX*room.getOPhong()+xCrac;
            double y =room.getyBox()-indexY*room.getOPhong()-yCrac;
            room.createCrac(x, y, xCrac, yCrac, zCrac, objRoom);
            return;
        }
        if(addRack == true){
            float xRack, yRack, zRack;
            if(Nut.RorateStatic == false){
                xRack = this.xRack;yRack = this.yRack;zRack=this.zRack;
            }
            else {
                xRack = this.yRack;yRack = this.xRack;zRack=this.zRack;
            }
            Point3d point = getPosition(e);
            point.x = point.x-xRack;
            point.y = point.y+yRack;
            int indexX = (int) ((point.x+room.getxBox())/room.getOPhong());
            int indexY=(int) ((room.getyBox()-point.y)/room.getOPhong());
            double x = -room.getxBox()+indexX*room.getOPhong()+xRack;
            double y =room.getyBox()-indexY*room.getOPhong()-yRack;
            int index = 0;
            if(data.getArrNut().size()!=0) index = Integer.parseInt(data.getArrNut().get(data.getArrNut().size()-1).getIndex())+1;
            Nut nut = new Nut(x, y, String.valueOf(index), "A", "B", "C", "D",this.xRack,this.yRack,this.zRack);
            data.getArrNut().add(nut);
            nut.createNode(group);
            return;
        }
        if(addGrill == true){
            Point3d point = getPosition(e);
            point.x = point.x-xRack;
            point.y = point.y+2*xRack;
            int indexX = (int) ((point.x+room.getxBox())/room.getOPhong());
            int indexY=(int) ((room.getyBox()-point.y)/room.getOPhong());
            double x = -room.getxBox()+indexX*room.getOPhong()+xRack;
            double y =room.getyBox()-indexY*room.getOPhong()-2*xRack;
            room.createGrill(x, y, objRoom);
            return;
        }
        }
        //
        pickcanvas = new PickCanvas(canvas, objRoom);
        pickcanvas.setMode(PickCanvas.BOUNDS);
        pickcanvas.setShapeLocation(e);
        PickResult result = pickcanvas.pickClosest();
        if(result != null){
                pointBeginLine = (Box)result.getNode(PickResult.PRIMITIVE);
                indexPoint = indexRoomContain(pointBeginLine);
                new SelectObjRoomJFrame(this, indexPoint).setVisible(true);
                return;
         }  
        //
        pickcanvas = new PickCanvas(canvas, group);
        pickcanvas.setMode(PickCanvas.BOUNDS);
        pickcanvas.setShapeLocation(e);
        result = pickcanvas.pickClosest();
        if(result!= null) {
            System.out.println("project_1.VeDoThi.mouseClicked()");
            pointBeginLine = (Box)result.getNode(PickResult.PRIMITIVE);
            indexPoint = indexPointContain(pointBeginLine);
            if(indexPoint!=-1)  new ThongTin(this, indexPoint).setVisible(true);
            return;
        }
        else {
            pickcanvas = new PickCanvas(canvas, group1);
            pickcanvas.setMode(PickCanvas.GEOMETRY);
            pickcanvas.setShapeLocation(e);
            result = pickcanvas.pickClosest();
            if(result != null) {
                Shape3D s = (Shape3D) result.getNode(PickResult.SHAPE3D);
                indexLine = indexLineContain(s);
                new ThongTinCanh(this, indexLine).setVisible(true);
                return;
            }
        }
        //
        
         
        

    }
         

    @Override
    public void mousePressed(MouseEvent e) {
        pointintersection = getPosition(e);
        pickcanvas = new PickCanvas(canvas, objRoom);
        pickcanvas.setMode(PickCanvas.BOUNDS);
        pickcanvas.setShapeLocation(e);
        PickResult result = pickcanvas.pickClosest();
        if(result!= null) {
            pointBeginLine = (Box)result.getNode(PickResult.PRIMITIVE);
        }
        else{
            pickcanvas = new PickCanvas(canvas, group);
            pickcanvas.setMode(PickCanvas.BOUNDS);
            pickcanvas.setShapeLocation(e);
            result = pickcanvas.pickClosest();
            if(result!= null) {
                pointBeginLine = (Box)result.getNode(PickResult.PRIMITIVE);
            }
            else pointBeginLine = null;
        }
	point = e.getPoint();
        candrag = true;
        beginpointdrag = e.getPoint();
        canvas.getPixelLocationInImagePlate(e.getX(), e.getY(), mousePos);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isFindPoint = true;
        if(indexPointBeginLine>=0){
            Point3d point = getPosition(e);
            float xRack, yRack, zRack;
            if(data.getArrNut().get(indexPointBeginLine).isXoay() == false){
                xRack = this.xRack;yRack = this.yRack;zRack=this.zRack;
            }
            else {
                xRack = this.yRack;yRack = this.xRack;zRack=this.zRack;
            }
            point.x = point.x-xRack;
            point.y = point.y+yRack;
            int indexX = (int) ((point.x+room.getxBox())/room.getOPhong());
            int indexY=(int) ((room.getyBox()-point.y)/room.getOPhong());
            double x = -room.getxBox()+indexX*room.getOPhong()+xRack;
            double y =room.getyBox()-indexY*room.getOPhong()-yRack;
            data.getArrNut().get(indexPointBeginLine).setPosition(x, y);
            indexPointBeginLine = -1;
            transGroup.setChild(updateLine(), 1);
        }
        
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(flagview == 0){
            Point3d point = getPosition(e);

        if (isFindPoint) {
            indexPointBeginLine = indexPointContain(pointBeginLine);
            indexObjRoom = indexRoomContain(pointBeginLine);           
                    isFindPoint = false;
        }
        if (indexPointBeginLine >= 0){
                data.getArrNut().get(indexPointBeginLine).setPosition(point.x, point.y);
                transGroup.setChild(updateLine(), 1);
                return;
                
        }
        if(indexObjRoom >=0 ){
            room.setPosition(indexObjRoom, point.x, point.y);
            return;
        }
        else{
            
                Point3d mousePos1 = new Point3d();
            canvas.getPixelLocationInImagePlate(e.getX(), e.getY(), mousePos1);
            objtrans = universe.getViewingPlatform().getViewPlatformTransform();
            trans = new Transform3D();
            Transform3D temp = new Transform3D();
            Point3d p = getPosition(e);
            universe.getViewingPlatform().getViewPlatformTransform().getTransform(temp);
            Vector3d tempvector = new Vector3d();
            temp.get(tempvector);
            double dx = mousePos1.x - mousePos.x;
            double dy = mousePos1.y - mousePos.y;
            Vector3d vectortranslate = new Vector3d(tempvector.x-dx*(tempvector.z*3), tempvector.y-dy*(tempvector.z*3),tempvector.z);
            trans.setTranslation(vectortranslate);
            objtrans.setTransform(trans);
            mousePos= mousePos1;
                    
            
        }
        }
        
    }
    public boolean candrag = true;
    public BranchGroup updateLine() { // update location line after move point
        group1 = new BranchGroup();
        group1.setCapability(BranchGroup.ALLOW_DETACH);
        group1.setCapability(Group.ALLOW_CHILDREN_WRITE);
        group1.setCapability(Group.ALLOW_CHILDREN_EXTEND);
		for (int i = 0; i < data.getArrCanh().size(); i++) {
                    int a = data.getArrCanh().get(i).getIndexPointA();
                        int b = data.getArrCanh().get(i).getIndexPointB();
                        int cost = data.getArrCanh().get(i).getCost();
                        Color cor = data.getArrCanh().get(i).getColorLine();
                        boolean Hide = data.getArrCanh().get(i).isIsHide();
                    if(data.getArrCanh().get(i) instanceof Line){
                        Canh e = new Line(data.getArrNut().get(a).getP(), data.getArrNut().get(b).getP(), a, b, cost,cor);
                        data.getArrCanh().set(i, e);
                        data.getArrCanh().get(i).setIsHide(Hide);
                        e.creatLine(group1);
                    }
                    else {
                        Canh e = new Canh2D(data.getArrNut().get(a).getP(), data.getArrNut().get(b).getP(), a, b, cost,cor);
                        data.getArrCanh().set(i, e);
                        data.getArrCanh().get(i).setIsHide(Hide);
                        e.creatLine(group1);
                     }
		}
            return group1;
	}
    //ve
    public void redraw(){
        for(int i = 0; i < data.getArrNut().size();i++){
            data.getArrNut().get(i).createNode(group);
        }
        for(int i = 0; i < data.getArrCanh().size();i++){
            data.getArrCanh().get(i).creatLine(group1);
            //System.out.println("project_1.VeDoThi.redraw()");
        }
    }
    public LineArray creatLine(Point3d p1, Point3d p2){
        LineArray axisXLines = new LineArray(2, LineArray.COORDINATES);
        axisXLines.setCoordinate(0, p1);
        axisXLines.setCoordinate(1, p2);
        return axisXLines;
    }
     private Shape creatLine2D(Point p1, Point p2){
        AffineTransform at = new AffineTransform();
        double theta = Math.atan2(p2.y - p1.y, p2.x - p1.x);
        double theta2 = Math.PI/2 -theta;
        double d = Math.sqrt(Math.pow(p2.y - p1.y,2)+Math.pow( p2.x - p1.x, 2));
        at.rotate(theta, p1.x , p1.y);
        
        Arc2D.Double l = new Arc2D.Double(p1.x, (p1.y - d*0.1), d, d*0.2, 0, 180, 0);
        Shape arc2 = at.createTransformedShape(l);        
        return arc2;
    }
    //Tim do
    protected int indexRoomContain(Box box){
        for (int i = 0; i < room.getArrTransObj().size(); i++) {
            Box t = (Box)room.getArrTransObj().get(i).getChild(0);
			if (t.equals(box)) {
				return i;
			}
		}
		return -1;
    }
    //Tim dinh
    protected int indexPointContain(Box box) {
		for (int i = 0; i < data.getArrNut().size(); i++) {
			if (data.getArrNut().get(i).getBox().equals(box)
                                ||data.getArrNut().get(i).getBox1().equals(box)) {
				return i;
			}
		}
		return -1;
	}
    //Tim canh
    protected int indexLineContain(Shape3D shape){
        for(int i = 0; i < data.getArrCanh().size(); i++){
		if (data.getArrCanh().get(i).getShape().equals(shape)
                        ||data.getArrCanh().get(i).getText().equals(shape)){
                    return i;
                }
        }
        return -1;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    public Appearance getAppearance(Color color) {
		return getAppearance(new Color3f(color));
	}
   public Appearance getAppearance(Color3f color) {
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
   public ArrayList<Canh> arrtemp(){
       ArrayList<Canh> arrtemp = new ArrayList<>();
            for(int i = 0; i < data.getArrCanh().size(); i++){
                arrtemp.add(data.getArrCanh().get(i));
            }
        return arrtemp;
   }
    public VeDoThi readFile(String name) throws ClassNotFoundException {
        VeDoThi g = new VeDoThi(0, 0);
        ArrayList<Canh> arrCanh = new ArrayList<>();
        ArrayList<Nut> arrNut = new ArrayList<>();
        try {
            FileReader f = new FileReader(name);
            Scanner str = new Scanner(f);
            try {
                 g.room.createRoom(str.nextDouble(), str.nextDouble(), str.nextFloat(), g.groupRoom);
                 String s = str.nextLine();
                 g.xRack = str.nextFloat();g.yRack = str.nextFloat();g.zRack = str.nextFloat();
                 s = str.nextLine();
                 s = str.nextLine();
            int n = 0;
            while(!s.equals("-1")){
                n++;
                String[] word = s.split(" ");
                for(int i = 0; i < word.length; i++){
                if(word[i].startsWith("\"")&&word[i].endsWith("\""))
                    word[i] = word[i].substring(1, word[i].length()-1);
            }  
                double x = Double.parseDouble(word[1]);
                double y = Double.parseDouble(word[2]);
                double z = Double.parseDouble(word[3]);
                int Xoay = Integer.parseInt(word[8]);
                int server = Integer.parseInt(word[9]);
                Nut p = new Nut(x,y,z, word[0], word[4], word[5], word[6], word[7],g.xRack,g.yRack,g.zRack);
                if(Xoay==1) Nut.RorateStatic = true;
                else Nut.RorateStatic= false;
                if(server==0) p.setEdge(false);
                else p.setServer(server);
                p.createNode(g.group);
                g.data.getArrNut().add(p);
                s = str.nextLine();
            }
            //int[][] a = new int[data.getArrNut().size()][data.getArrNut().size()];
            s = str.nextLine();
            while(!s.equals("-1")){
                String[] word = s.split(" ");
                int a = Integer.parseInt(word[0]);
                int b = Integer.parseInt(word[1]);
                Point3d p1 = g.data.getArrNut().get(a).getP();
                Point3d p2 = g.data.getArrNut().get(b).getP();
                if(Integer.parseInt(word[3])==0){
                    Canh e3 = new Canh2D(p1, p2, a, b, Integer.parseInt(word[2]), Color.WHITE);
                    g.data.getArrCanh().add(e3);
                    e3.creatLine(g.group1);
                }
                else{
                    Canh e3 = new Line(p1, p2, a, b, Integer.parseInt(word[2]), Color.WHITE);
                    g.data.getArrCanh().add(e3);
                    e3.creatLine(g.group1);
                }
                s = str.nextLine();
            }
            g.xCrac = str.nextFloat();g.yCrac=str.nextFloat();g.zCrac=str.nextFloat();
            g.xPDU = str.nextFloat();g.yPDU=str.nextFloat();g.zPDU=str.nextFloat();
            s = str.nextLine();
            s = str.nextLine();
            while(!s.equals("-1")){
                String[] word = s.split(" ");
                int ThuTu = Integer.parseUnsignedInt(word[0]);
                double x = Double.parseDouble(word[1]);
                double y = Double.parseDouble(word[2]);
                int Xoay = Integer.parseInt(word[3]);
                
                if(Xoay == 1){
                    g.room.setRorate(true);
                }
                else g.room.setRorate(false);
                if(ThuTu == 1) g.room.createCrac(x, y, xCrac, yCrac, zCrac, g.objRoom);
                else if(ThuTu == 2) g.room.createPDU(x, y, xPDU, yPDU, zPDU, g.objRoom);
                else if(ThuTu == 3) g.room.createGrill(x, y, g.objRoom);
                s = str.nextLine();
            }
            f.close();
            g.temparr = g.arrtemp();
            return  g;
            } catch (java.util.InputMismatchException e) {
                JOptionPane.showMessageDialog(null, "File đầu vào chưa đúng");
            }
           
            
        } catch (IOException e) {
            System.out.println("Loi");
        }
        return g;
    }
    public void writeFile(String name){
        try {
            FileWriter f = new FileWriter(name+".txt");
            f.write(room.getxBox()+" "+room.getyBox()+" "+room.getOPhong()+"\n");
            f.write(xRack+" "+yRack+" "+zRack+"\n");
            for(int i = 0; i < data.getArrNut().size(); i++){
                Nut p =data.getArrNut().get(i);
                String a = data.getArrNut().get(i).getA();
                String b = data.getArrNut().get(i).getB();
                String c = data.getArrNut().get(i).getC();
                String d = data.getArrNut().get(i).getD();
                int Xoay;
                if(p.isXoay()==false) Xoay = 0;
                else Xoay =1;
                f.write(p.getIndex()+" "+p.getX()+" "+p.getY()+" "+p.getZ()+" \""+a+"\" \""+b+"\" \""+c+"\" \""+d+"\" "+Xoay+" "+p.getServer()+"\n");
                f.flush();
            }
            f.write("-1\n");
            for(int i = 0; i < data.getArrCanh().size(); i++){
                if(data.getArrCanh().get(i)instanceof Line)
                    f.write(data.getArrCanh().get(i).getIndexPointA()+" "+data.getArrCanh().get(i).getIndexPointB()+" "+data.getArrCanh().get(i).getCost()+" "+1);
                else f.write(data.getArrCanh().get(i).getIndexPointA()+" "+data.getArrCanh().get(i).getIndexPointB()+" "+data.getArrCanh().get(i).getCost()+" "+0);
                f.write("\n");
            }
            f.write("-1\n");
            f.write(xCrac+" "+yCrac+" "+zCrac+"\n");
            f.write(xPDU+" "+yPDU+" "+zPDU+"\n");
            for(int i = 0; i < room.getArrTransObj().size(); i++){
                Transform3D trans = new Transform3D();
                room.getArrTransObj().get(i).getTransform(trans);
                Vector3d vector = new Vector3d();
                trans.get(vector);
                int Xoay;
                if(room.getXoay().get(i) == false) Xoay = 0;
                else Xoay = 1;
                f.write(room.getThuTu().get(i)+" "+vector.x+" "+vector.y+" "+Xoay+"\n");
            }
            f.write("-1");
            f.close();
            JOptionPane.showMessageDialog(null, "Save success", "Save success",
					JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Save", "Error save file",
            JOptionPane.OK_OPTION);
            System.out.println("Error save file\n" + e.toString());
	}
    }
    public int[][] DataToMatrix() {
	int size = data.getArrNut().size();
	int[][] a = new int[size][size];
	for (int i = 0; i < data.getArrCanh().size(); i++) {
            a[data.getArrCanh().get(i).getIndexPointA()][data.getArrCanh().get(i)
				.getIndexPointB()] = data.getArrCanh().get(i).getCost();
            a[data.getArrCanh().get(i).getIndexPointB()][data.getArrCanh().get(i)
				.getIndexPointA()] = data.getArrCanh().get(i).getCost();
            }
        return a;
	}
    public boolean canhTorus(int a, int b,int dinh){
        int x, y;
        if(a<b){
            x = a; y = b;
        }
        else{
            x = b; y = a;
        }
        int n = (int) Math.sqrt(dinh);
        if(x < n && y == n*(n-1)+x) return true;
        if(x%n == 0&&y == x + n-1) return true;
        return false;
    }
    Point3d getIntersection(Point3d line1, Point3d line2, 
		Point3d plane1, Point3d plane2, Point3d plane3) {
	Vector3d p1 = new Vector3d(plane1);
	Vector3d p2 = new Vector3d(plane2);
	Vector3d p3 = new Vector3d(plane3);
	Vector3d p2minusp1 = new Vector3d(p2);
	p2minusp1.sub(p1);
	Vector3d p3minusp1 = new Vector3d(p3);
	p3minusp1.sub(p1);
	Vector3d normal = new Vector3d();
	normal.cross(p2minusp1, p3minusp1);
	// The plane can be defined by p1, n + d = 0
	double d = -p1.dot(normal);
	Vector3d i1 = new Vector3d(line1);
	Vector3d direction = new Vector3d(line1);
	direction.sub(line2);
	double dot = direction.dot(normal);
	if (dot == 0) return null;
	double t = (-d - i1.dot(normal)) / (dot);
	Vector3d intersection = new Vector3d(line1);
	Vector3d scaledDirection = new Vector3d(direction);
	scaledDirection.scale(t);
	intersection.add(scaledDirection);
	Point3d intersectionPoint = new Point3d(intersection);
	return intersectionPoint;
    }
    public Point3d getPosition(MouseEvent event) {
		Point3d eyePos = new Point3d();
		Point3d mousePos = new Point3d();
		canvas.getCenterEyeInImagePlate(eyePos);
		canvas.getPixelLocationInImagePlate(event.getX(), event.getY(), mousePos);
		Transform3D transform = new Transform3D();
		canvas.getImagePlateToVworld(transform);
		transform.transform(eyePos);
		transform.transform(mousePos);
		Vector3d direction = new Vector3d(eyePos);
		direction.sub(mousePos);
                TransformGroup g = universe.getViewingPlatform().getViewPlatformTransform();
                Transform3D d = new Transform3D();
                g.getTransform(d);
                Vector3d n = new Vector3d();
                d.get(n);
		// three points on the plane
		Point3d p1 = new Point3d(10.5, -10.5, 0.0);
		Point3d p2 = new Point3d(10.5, 10.5, 0.0);
		Point3d p3 = new Point3d(-10.5, 10.5, 0.0);
		Transform3D currentTransform = new Transform3D();		
		Point3d intersection = getIntersection(eyePos, mousePos, p1, p2, p3);
		return intersection;		
	}

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}




