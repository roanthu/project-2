/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.geometry.Point3D;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Polygon;
import javax.media.j3d.LineArray;
import javax.media.j3d.TransformGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

/**
 *
 * @author Roan Thu
 */
public class Data implements Serializable{
    private int[][] a;
    private ArrayList<Nut> arrNut;
    private ArrayList<Canh> arrCanh;
    final private int r = 15, r2 = 30;
    private float tyle;
    boolean Torus;
    private VeDoThi dothi;
    private int map;

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }
    public boolean isTorus() {
        return Torus;
    }

    public void setTorus(boolean Torus) {
        this.Torus = Torus;
    }

    public float getTyle() {
        return tyle;
    }

    public void setTyle(float tyle) {
        this.tyle = tyle;
    }
    public int[][] getA() {
        return a;
    }

    public void setA(int[][] a) {
        this.a = a;
    }

    public ArrayList<Nut> getArrNut() {
        return arrNut;
    }

    public void setArrNut(ArrayList<Nut> arrNut) {
        this.arrNut = arrNut;
    }

    public ArrayList<Canh> getArrCanh() {
        return arrCanh;
    }

    public void setArrCanh(ArrayList<Canh> arrCanh) {
        this.arrCanh = arrCanh;
    }

    public Data() {
        this.arrNut = new ArrayList<>();
        this.arrCanh = new ArrayList<>();
    }
    //ran dom
    public Data(int SoDinh, int SoCanh) {
        this.arrNut = new ArrayList<>();
        this.arrCanh = new ArrayList<>();
        Random rd = new Random();
        for (int i = 0; i < SoDinh; i++) {
            
            double x = (rd.nextInt(2)-1)+ Math.random();
            double y =  (rd.nextInt(2)-1)+Math.random();
            
            int a = rd.nextInt(100);
            int b = rd.nextInt(100);
            int c = rd.nextInt(100);
            int d = rd.nextInt(100);
            Nut e = new Nut(x,y, String.valueOf(i), String.valueOf(a), String.valueOf(b), String.valueOf(c), String.valueOf(d));
            arrNut.add(e);
	}
        int count = 0;
        while(count < SoCanh){
            int a = rd.nextInt(SoDinh); int b = rd.nextInt(SoDinh);
            if(a == b || inArrCanh(a, b)) continue;
            else{
                int x = 0,y = 0;
                Canh e = new Line(arrNut.get(a).getP(), arrNut.get(b).getP(), a, b, rd.nextInt(200),Color.WHITE);
                arrCanh.add(e); 
                count++;
            }
        }
    }
    //2d 2d torus mesh
    public Data(int m, int n, boolean Torus){
        this.arrNut = new ArrayList<>();
        this.arrCanh = new ArrayList<>();
        Random rd = new Random();
        double D = 0.2;
        double height = (m-1)*0.2;
        double wight = (n-1)*0.2;
            for (int i = 0; i < m; i++) {
            double x = -wight/2;
            double y = height/2 - i*0.2;
             for(int j = 0; j < n; j++){
                x = -wight/2 + j*0.2;
            int a = rd.nextInt(100);
            int b = rd.nextInt(100);
            int c = rd.nextInt(100);
            int d = rd.nextInt(100);
            Nut e = new Nut(x,y, String.valueOf(i*n+j), String.valueOf(a), String.valueOf(b), String.valueOf(c), String.valueOf(d));
            arrNut.add(e);
             }
	}  
        

        this.Torus = Torus;
        for(int i = 0; i < m; i++){
            if(Torus == true){
                Point3d p1 = arrNut.get( i).getP();
                Point3d p2 = arrNut.get( m*(m-1) + i) .getP();
                Canh e3 = new Canh2D(p1,p2, i, m*(m-1) +i,1 + rd.nextInt(200),Color.WHITE);
                arrCanh.add(e3);
            }
            for(int j = 0; j < n - 1; j++){
                Point3d p1 = arrNut.get(i*n + j).getP();
                Point3d p2 = arrNut.get(i*n + j+1) .getP();
                Canh e = new Line(p1,p2, i*n + j, i*n + j+1,1 + rd.nextInt(200),Color.WHITE);
                arrCanh.add(e);
                
            }
        } 
        for(int i = 0; i < n; i++){
            if(Torus == true){
                Point3d p1 = arrNut.get(i*n).getP();
                Point3d p2 = arrNut.get((i+1)*n-1) .getP();
                Canh e4 = new Canh2D(p1,p2, i*n, (i+1)*n-1,1 + rd.nextInt(200),Color.WHITE);
                arrCanh.add(e4);
            }
            for(int j = 0; j < m -1; j++){
                Point3d p1 = arrNut.get(i + n*j).getP();
                Point3d p2 = arrNut.get(i + n*(j+1)) .getP();
                Canh e2 = new Line(p1,p2, i + n*j, i + n*(j+1),1 + rd.nextInt(200),Color.WHITE);
                arrCanh.add(e2);
            }
        }
    }
    //3 4 5 6d cube
    public Data(int n, double x, double y,int begin){ 
        this.arrNut = new ArrayList<>();
        this.arrCanh = new ArrayList<>();
        Random rd = new Random();
        double x1 = x;
        double y1 = y;
        if(n == 3){
                y = y - 0.3;
                for (int i = 0; i < 2; i++) {
                    y1 = y - 0.3*i;
                    for(int j = 0; j < 2; j++){
                        if(i == 0) x1 = x + 0.3*j;
                        else x1 = x + 0.3 -0.3*j;
                        int a = rd.nextInt(100);
                        int b = rd.nextInt(100);
                        int c = rd.nextInt(100);
                        int d = rd.nextInt(100);
                        Nut e = new Nut(x1,y1, String.valueOf(i*2+j+begin), String.valueOf(a), String.valueOf(b), String.valueOf(c), String.valueOf(d));
                        arrNut.add(e);
                    }
                }
                for (int i = 0; i < 2; i++) {
                    y1 = y + 0.15 - 0.3*i;
                    for(int j = 0; j < 2; j++){
                        if(i == 0) x1 = x + 0.15 + 0.3*j;
                        else x1 = x + 0.45 -0.3*j ;
                        int a = rd.nextInt(100);
                        int b = rd.nextInt(100);
                        int c = rd.nextInt(100);
                        int d = rd.nextInt(100);
                        Nut e = new Nut(x1,y1, String.valueOf(i*2+j+4+begin), String.valueOf(a), String.valueOf(b), String.valueOf(c), String.valueOf(d));
                        arrNut.add(e);
                     }
                } 
            
        for (int i = 0; i < 4; i++){
            Point3d p1 = arrNut.get(i).getP();
            Point3d p2 = arrNut.get((i+1)%4).getP();
            Canh e = new Line(p1,p2, begin+i,begin+(i+1)%4,1 + rd.nextInt(200),Color.WHITE);
            arrCanh.add(e);
            p1 = arrNut.get(i+4).getP();
            p2 = arrNut.get((i+1)%4 + 4).getP();
            Canh e2 = new Line(p1,p2, begin+i + 4,begin+(i+1)%4 +4,1 + rd.nextInt(200),Color.WHITE);
            arrCanh.add(e2);
            p1 = arrNut.get(i).getP();
            p2 = arrNut.get(i+4).getP();
            Canh e3 = new Line(p1,p2, begin+i ,begin+i +4,1 + rd.nextInt(200),Color.WHITE);
            arrCanh.add(e3);
        }
    }  
        Data data1 = new Data();
        Data data2 = new Data();
        if(n == 4){
            data1 = new Data(n-1, x - 0.3, y +0.3,begin);
            data2= new Data(n-1, x + 0.3 ,y - 0.1,begin+8);
            
        }
        if(n == 5){
            data1 = new Data(n-1, x - 0.3, y +0.3,begin);
            data2= new Data(n-1, x + 0.6 ,y + 0.6,begin+16);
            
        }
        if(n == 6){
            data1 = new Data(n-1, x - 0.3, y +0.3,begin);
            data2= new Data(n-1, x + 1.0 ,y - 1.0,begin+32);
            
        }
        if(n>3){
                
        for(int i = 0; i < Math.pow(2, n-1); i++){
                arrNut.add(data1.getArrNut().get(i));
            }
            for(int i = 0; i < Math.pow(2, n-1); i++){
                int k = (int)Math.pow(2, n-1);
                arrNut.add(data2.getArrNut().get(i));
            }

            for(int i = 0; i < Math.pow(2, n-2)*(n-1); i++){
                arrCanh.add(data1.getArrCanh().get(i));
                int a = data2.getArrCanh().get(i).getIndexPointA();
                int b = data2.getArrCanh().get(i).getIndexPointB();
                arrCanh.add(data2.getArrCanh().get(i));
                
            }
            for(int i = 0; i < Math.pow(2,n-1); i++){
                int k = (int) Math.pow(2, n-1);
                Point3d p1 = arrNut.get(i).getP();
                Point3d p2 = arrNut.get(i+k).getP();
                Canh e = new Line(p1,p2, begin + i,begin + i+k,1 + rd.nextInt(200),Color.WHITE);
                arrCanh.add(e); 
            }
        
    }   
    }
    //layout
    Data(double wight,double height, double dis,float xBox,float yBox,float zBox){
        this.arrNut = new ArrayList<>();
        this.arrCanh = new ArrayList<>();
        Random rd = new Random();
        int m = 0, n = 0;
        double d1 = dis+yBox*2;
        double d2 = dis+xBox*2;
        if(2*xBox < (float)(wight +0.01)&& 2*yBox < (float)(height + 0.01)){
            m = (int) ((height-yBox*2)/d1)+1;
            n = (int)  ((wight-xBox*2)/d2)+1;
        }
            for (int i = 0; i < m; i++) {
            double x = -wight/2;
            double y = height/2 - yBox - i*d1;

            for(int j = 0; j < n; j++){
                x = -wight/2+xBox + j*d2;
            int a = rd.nextInt(100);
            int b = rd.nextInt(100);
            int c = rd.nextInt(100);
            int d = rd.nextInt(100);
            Nut e = new Nut(x,y, String.valueOf(i*n+j), String.valueOf(a), String.valueOf(b), String.valueOf(c), String.valueOf(d),xBox,yBox,zBox);
            arrNut.add(e);
             }
	}  
        

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n - 1; j++){
                Point3d p1 = arrNut.get(i*n + j).getP();
                Point3d p2 = arrNut.get(i*n + j+1) .getP();
                Canh e = new Line(p1,p2, i*n + j, i*n + j+1,1 + rd.nextInt(200),Color.WHITE);
                arrCanh.add(e);
                
            }
        } 
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m -1; j++){
                Point3d p1 = arrNut.get(i + n*j).getP();
                Point3d p2 = arrNut.get(i + n*(j+1)) .getP();
                Canh e2 = new Line(p1,p2, i + n*j, i + n*(j+1),1 + rd.nextInt(200),Color.WHITE);
                arrCanh.add(e2);
            }
        }
    }
    public Data(int m, int n,int span){
        this.arrNut = new ArrayList<>();
        this.arrCanh = new ArrayList<>();
        Random rd = new Random();
        double D = 0.2;
        double height = (m-1)*0.2;
        double wight = (n-1)*0.2;
            for (int i = 0; i < m; i++) {
            double x = -wight/2;
            double y = height/2 - i*0.2;
             for(int j = 0; j < n; j++){
                x = -wight/2 + j*0.2;
            int a = rd.nextInt(100);
            int b = rd.nextInt(100);
            int c = rd.nextInt(100);
            int d = rd.nextInt(100);
            Nut e = new Nut(x,y, String.valueOf(i*n+j), String.valueOf(a), String.valueOf(b), String.valueOf(c), String.valueOf(d));
            arrNut.add(e);
             }
	}  
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n - 1; j++){
                Point3d p1 = arrNut.get(i*n + j).getP();
                Point3d p2 = arrNut.get(i*n + j+1) .getP();
                Canh e = new Line(p1,p2, i*n + j, i*n + j+1,1 + rd.nextInt(200),Color.WHITE);
                arrCanh.add(e);
                
            }
        } 
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m -1; j++){
                Point3d p1 = arrNut.get(i + n*j).getP();
                Point3d p2 = arrNut.get(i + n*(j+1)) .getP();
                Canh e2 = new Line(p1,p2, i + n*j, i + n*(j+1),1 + rd.nextInt(200),Color.WHITE);
                arrCanh.add(e2);
            }
        }
        int count = 0;
        while(count < span){
            int a = rd.nextInt(m*n-1); int b = rd.nextInt(m*n-1);
            if(a == b || inArrCanh(a, b)) continue;
            else{
                Canh e = new Canh2D(arrNut.get(a).getP(), arrNut.get(b).getP(), a, b, rd.nextInt(200),Color.WHITE);
                arrCanh.add(e); 
                count++;
            }
        }
    }
    //fat tree
    Data(int port){
        this.arrNut = new ArrayList<>();
        this.arrCanh = new ArrayList<>();
        Random rd = new Random();
        double D = 0.2;
        double height = (port-1)*0.3;
        double wight = (port/2-1)*0.3;
        int coreswitch = (int) Math.pow((port/2), 2);
        for(int i = 0; i < coreswitch; i++){//core switch
            double x = -height/2 + i*0.2;
            double y = wight/4;
            if(i>=coreswitch/2) {
                x = -height/2 + (i-coreswitch/2)*0.2;
                y = -wight/4 ;
            }
                int a = rd.nextInt(100);
                int b = rd.nextInt(100);
                int c = rd.nextInt(100);
                int d = rd.nextInt(100);
                Nut e = new Nut(x,y, 0.8,String.valueOf(i), String.valueOf(a), String.valueOf(b), String.valueOf(c), String.valueOf(d));
                e.setEdge(false);
                arrNut.add(e);
        }
        int aggswitch = (int) (Math.pow(port, 2)/2);
        for(int i=0; i < port; i++){
            double x = -height/2 + i*0.3;
            double y = wight/2;
            for(int j = 0; j < port/2; j++){
                y =  wight/2 - j*0.3;
                int a = rd.nextInt(100);
                int b = rd.nextInt(100);
                int c = rd.nextInt(100);
                int d = rd.nextInt(100);
                int index = arrNut.size();
                Nut e = new Nut(x,y, 0.3,String.valueOf(index), String.valueOf(a), String.valueOf(b), String.valueOf(c), String.valueOf(d));
                e.setEdge(false);
                arrNut.add(e);
            }
        }
        for(int i=0; i < port; i++){//create rack
            double x = -height/2 + i*0.3;
            double y = wight/2;
            for(int j = 0; j < port/2; j++){
                y =  wight/2 - j*0.3;
                int a = rd.nextInt(100);
                int b = rd.nextInt(100);
                int c = rd.nextInt(100);
                int d = rd.nextInt(100);
                int index = arrNut.size();
                Nut e = new Nut(x,y,String.valueOf(index), String.valueOf(a), String.valueOf(b), String.valueOf(c), String.valueOf(d));
                e.setServer(port/2);              
                arrNut.add(e);
            }
        }
        //create canh
        for(int i=0; i < aggswitch; i++){
            int index = coreswitch+i;
            int p = i%(port/2);
            for(int j = p*port/2; j < p*port/2+port/2; j++){
                    Canh l = new Line(arrNut.get(j).getP(), arrNut.get(index).getP(), j, index, rd.nextInt(200),Color.WHITE);
                    arrCanh.add(l); 
            }
        }
        for(int i=0; i < aggswitch; i++){
            int index = coreswitch+aggswitch+i;
            int pod = i/(port/2);
            for(int j = coreswitch + pod*port/2;j < coreswitch + pod*port/2 +port/2;j++){
                Canh l = new Line(arrNut.get(j).getP(), arrNut.get(index).getP(), j, index, rd.nextInt(200),Color.WHITE);
                arrCanh.add(l); 
            }
        }
        
    }
    Data(int sever,int Switch, int port,boolean jellyfish){
        this.arrNut = new ArrayList<>();
        this.arrCanh = new ArrayList<>();
        ArrayList<Integer> indexNut = new ArrayList<>();
        Random rd = new Random();
        double D = 0.2;
        int rack,aggswitch,cnsever;
        if((int)(sever/Switch)>=(port-1)){
            JOptionPane.showMessageDialog(null, "Số server quá lớn");
            return;
        }
        if(sever < Switch){
            rack = sever;
            aggswitch = Switch - sever;
            cnsever = 0;
        }
        else {
            rack = Switch;
            aggswitch = 0;
            cnsever = sever/Switch;
        }
        int n = (int) Math.sqrt(rack);
        double height = (Math.sqrt(rack))*0.3;
        double wight = (Math.sqrt(rack))*0.3;
        for(int i = 0; i < aggswitch; i++){// aggswitch
            double x = ((rd.nextInt(2)-1)+ Math.random())/2;
            double y =  ((rd.nextInt(2)-1)+Math.random())/2;
                int a = rd.nextInt(100);
                int b = rd.nextInt(100);
                int c = rd.nextInt(100);
                int d = rd.nextInt(100);
                Nut e = new Nut(x,y, 0.5,String.valueOf(i), String.valueOf(a), String.valueOf(b), String.valueOf(c), String.valueOf(d));
                e.setEdge(false);
                e.setFreeport(port);
                e.setServer(0);
                arrNut.add(e);
                indexNut.add(arrNut.size()-1);
        }
        for(int i=0; i < n; i++){//create rack
            double x = -height/2 + i*0.3;
            double y = wight/2;
            for(int j = 0; j < n; j++){
                y =  wight/2 - j*0.3;
                int a = rd.nextInt(100);
                int b = rd.nextInt(100);
                int c = rd.nextInt(100);
                int d = rd.nextInt(100);
                int index = arrNut.size();
                Nut e = new Nut(x,y,String.valueOf(index), String.valueOf(a), String.valueOf(b), String.valueOf(c), String.valueOf(d));
                if(i*n+j<(sever%Switch)) {
                    e.setFreeport(port-cnsever-1);
                    e.setServer(cnsever+1);
                }
                else {
                    e.setFreeport(port-cnsever);
                    e.setServer(cnsever);
                }
                
                arrNut.add(e);
                indexNut.add(arrNut.size()-1);
            }
            if(i == n-1){
                x = -height/2 + (i+1)*0.3;
                for(int j = 0; j < rack-n*n; j++){
                y =  wight/2 - j*0.3;
                int a = rd.nextInt(100);
                int b = rd.nextInt(100);
                int c = rd.nextInt(100);
                int d = rd.nextInt(100);
                int index = arrNut.size();
                Nut e = new Nut(x,y,String.valueOf(index), String.valueOf(a), String.valueOf(b), String.valueOf(c), String.valueOf(d));
                if(i*n+j<(sever%Switch)) {
                    e.setFreeport(port-cnsever-1);
                    e.setServer(cnsever+1);
                }
                else {
                    e.setFreeport(port-cnsever);
                    e.setServer(cnsever);
                }
                arrNut.add(e);
                indexNut.add(arrNut.size()-1);
            }
            }
        }
        //create Canh
        int count = 0;
        while(indexNut.size()>1){
            int a = rd.nextInt(indexNut.size());
            int b = rd.nextInt(indexNut.size());
            if(a==b||inArrCanh(indexNut.get(a), indexNut.get(b))){
                count++;
                System.out.println("project_1.Data.<init>()"+CoTheThem(0, indexNut));
                if(count > 10){
                    if(CoTheThem(0, indexNut) == false) break;
                }
                continue;
            }
            else{
                count = 0;
                Canh e = new Line(arrNut.get(indexNut.get(a)).getP(), arrNut.get(indexNut.get(b)).getP(), indexNut.get(a), indexNut.get(b), rd.nextInt(200),Color.WHITE);
                arrCanh.add(e);
                int freeportA = arrNut.get(indexNut.get(a)).getFreeport();
                arrNut.get(indexNut.get(a)).setFreeport(freeportA-1);
                int freeportB = arrNut.get(indexNut.get(b)).getFreeport();
                arrNut.get(indexNut.get(b)).setFreeport(freeportB-1);
                if(a>b){
                    if(freeportA-1 == 0) indexNut.remove(a);
                    if(freeportB-1 == 0) indexNut.remove(b);
                }
                else{
                    if(freeportB-1 == 0) indexNut.remove(b);
                    if(freeportA-1 == 0) indexNut.remove(a);
                }
                System.out.println("project_1.Data.<init>()"+indexNut.size());
            }
            
        }
        for(int i = 0; i < indexNut.size(); i++){
            System.out.println("project_1.Data.<init>() freeport"+indexNut.get(i)+" "+arrNut.get(indexNut.get(i)).getFreeport());
            int free = arrNut.get(indexNut.get(i)).getFreeport();
            while(free >= 2){
                int indexCanh = rd.nextInt(arrCanh.size()-1);
                int a = arrCanh.get(indexCanh).getIndexPointA();
                int b = arrCanh.get(indexCanh).getIndexPointB();
                while(a==indexNut.get(i)||b==indexNut.get(i)){
                    indexCanh = rd.nextInt(arrCanh.size()-1);
                    a = arrCanh.get(indexCanh).getIndexPointA();
                    b = arrCanh.get(indexCanh).getIndexPointB();
                }
                System.out.println("project_1.Data.<init>()"+a +" "+ b);
                Canh e = new Line(arrNut.get(a).getP(), arrNut.get(indexNut.get(i)).getP(), a, indexNut.get(i), rd.nextInt(200),Color.WHITE);
                arrCanh.add(e);
                Canh e1 = new Line(arrNut.get(b).getP(), arrNut.get(indexNut.get(i)).getP(), b, indexNut.get(i), rd.nextInt(200),Color.WHITE);
                arrCanh.add(e1);
                arrCanh.remove(indexCanh);
                arrNut.get(indexNut.get(i)).setFreeport(free-2);
                free = free-2;
            }
            System.out.println("project_1.Data.<init>()"+indexNut.get(i)+" "+arrNut.get(indexNut.get(i)).getFreeport());
        }
        
    }
    public LineArray creatLine(Point3d p1, Point3d p2){
        LineArray axisXLines = new LineArray(2, LineArray.COORDINATES);
        axisXLines.setCoordinate(0, p1);
        axisXLines.setCoordinate(1, p2);
        return axisXLines;
    }
    
    private boolean inArrCanh(int a, int b){
        for(int i = 0; i < arrCanh.size(); i++){
            int A = arrCanh.get(i).getIndexPointA();
            int B = arrCanh.get(i).getIndexPointB();
            if((a == A && b == B) || (a == B)&&(b == A)) return true;
        }
        return false;
    }
    private boolean CoTheThem(int a,ArrayList<Integer> por){
        if(a==por.size()) return false;
        for(int i = a+1;i < por.size(); i++){
            if(inArrCanh(por.get(a), por.get(i))) continue;
            else return true;
        }
        return CoTheThem(a+1, por);
    }
}
