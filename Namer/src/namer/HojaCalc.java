/*
 * Clase HojaCalc perteneciente al paquete namer
 */
package namer;


import jxl.JXLException.*;
import java.io.*;
import jxl.*;
import jxl.read.biff.BiffException;
import ou.*;

/**
 *
 * @author Xabier
 * @version 1.0
 * Clase hojas de calculo excel
 */
public class HojaCalc {
    
    private String pathName;
    private Workbook aBook;
  
    
    /**
     * Constructor del objeto HojaCalc
     * @param aPathName ruta del archivo excel
     * 
     */
    public HojaCalc(String aPathName){
      
      
      this.pathName = aPathName; 
      
      WorkbookSettings settings; 
      settings = new WorkbookSettings();
      settings.setSuppressWarnings(true);
      
      try{
           this.aBook = Workbook.getWorkbook(new File(this.pathName),settings);
           
      }
      catch(IOException e1){
          
          OUDialog.alert("ERROR: Campos vacios\n " + e1);
          
      }
      catch(BiffException e2){
       
          OUDialog.alert("ERROR: Archivo excel no es  compatible\n " + e2);
          
      } 
      
        
    }
    /**
     * 
     * @return Retorna el path del archivo excel
     * @see String
     * 
     */
    public String getPathName(){
        
        return this.pathName;
    }
    /**
     * 
     * @return Retorna la hoja de calculo
     * @see Workbook
     *  
     */
    public Workbook getBook(){
        
        return this.aBook;
    }
   
    
    /**
     * 
     * @param num numero del Sheet
     * @return Retorna la sheet de la hoja de calculo indicado por 'num'
     * @see Sheet 
     * 
     */
   public  Sheet getASheet(int num){
       
       return this.aBook.getSheet(num);
       
   }       
}
