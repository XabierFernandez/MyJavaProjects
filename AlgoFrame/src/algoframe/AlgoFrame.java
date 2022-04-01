/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoframe;

import java.awt.Toolkit;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Xabier
 */
public class AlgoFrame {

    private double[] p1;
    private double[] p2;
    private double[] p3;
    
    private  String XYZ;
    private  String ABC;
    
    public AlgoFrame(){
        // TODO code application logic here
        p1=new double[3];
        p2=new double[3];
        p3=new double[3];
        
    }
    
    private void setXYZ(double aFloat1,double aFloat2,double aFloat3){
        
        XYZ= Double.toString(aFloat1)+ ", " + Double.toString(aFloat2)+ ", " + Double.toString(aFloat3);
    }
    
    private void setABC(double aFloat1,double aFloat2,double aFloat3){
        
        ABC= Double.toString(aFloat1)+ ", " + Double.toString(aFloat2)+ ", " + Double.toString(aFloat3);
    }
    
    public String getXYZ(){
       
        setXYZ(getP1(0),getP1(1),getP1(2));
        return XYZ;
    }
    public String getABC(){
        return ABC;
    }
    
    private double getP1(int aNum){
        return p1[aNum];
    }
    private double getP2(int aNum){
        return p2[aNum];
    }
    private double getP3(int aNum){
        return p3[aNum];
    }
    
    public void setP1(String aFloat,int aNum){
        try{
            p1[aNum]=Double.parseDouble(aFloat);
        }catch(Exception e){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"Formato numero introducido erroneo, en P1\n","Alert",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void setP2(String aFloat,int aNum){
        try{
            p2[aNum]=Double.parseDouble(aFloat);
        }catch(Exception e){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"Formato numero introducido erroneo, en P2\n","Alert",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void setP3(String aFloat,int aNum){
        try{
            p3[aNum]=Double.parseDouble(aFloat);
        }catch(Exception e){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null,"Formato numero introducido erroneo, en P3\n","Alert",JOptionPane.WARNING_MESSAGE);
            
        }
    }
    public void calcular(){
        double tempP12x = p2[0] - p1[0];
	double tempP12y = p2[1] - p1[1];
	double tempP12z = p2[2] - p1[2];
      
	double tempP13x = p3[0] - p1[0];	
	double tempP13y = p3[1] - p1[1];		
	double tempP13z = p3[2] - p1[2];	


	double modTempP12 =  Math.sqrt(Math.pow(tempP12x,2) + Math.pow(tempP12y,2) + Math.pow(tempP12z,2));
        double modTempP13 =  Math.sqrt(Math.pow(tempP13x,2) + Math.pow(tempP13y,2) + Math.pow(tempP13z,2));
	
	
	double tempIx = tempP12x / modTempP12;
	double tempIy = tempP12y / modTempP12;
	double tempIz = tempP12z / modTempP12;

	double tempUx = tempP13x / modTempP13;
	double tempUy = tempP13y / modTempP13;
	double tempUz = tempP13z / modTempP13;

        double tempK1  = (tempIy * tempUz) - (tempIz * tempUy);
 	double tempK2  = (tempIz * tempUx) - (tempIx * tempUz);
	double tempK3  = (tempIx * tempUy) - (tempIy * tempUx);

	double tempJ1 = (tempIy * tempK3) - (tempIz * tempK2);
 	double tempJ2 = (tempK3 * tempIx) - (tempK1 * tempIz);
	double tempJ3 = (tempK1 * tempIy) - (tempK2 * tempIx);
        
        
        double tempTerm1 = Math.round(100000.0 * (Math.toDegrees(Math.atan2(tempIy,tempIx))))/100000.0;
        double tempTerm2 = Math.round(100000.0 * Math.toDegrees(Math.asin(-tempIz)))/100000.0;
        double tempTerm3 = Math.round(100000.0 * (Math.toDegrees(Math.atan2(tempJ3,tempK3))))/100000.0;
        setABC(tempTerm1,tempTerm2,tempTerm3); 
        
	
    }
    
}
