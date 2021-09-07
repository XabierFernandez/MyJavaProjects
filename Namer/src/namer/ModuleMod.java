/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package namer;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import ou.OUDialog;

/**
 *
 * @author Xabier
 */
public class ModuleMod {
    
    private String pathname;
    private File aFile;
    private BufferedWriter aFileWriter;
    
    /**
     * Constructor del objecto ModuleMod
     * archivos .mod modificados
     * @param aPathName 
     */
    public ModuleMod(String aPathName){
                    
        try{
            this.pathname = aPathName;
            this.aFile = new File(this.pathname);
            this.aFileWriter = new BufferedWriter(new FileWriter(aFile));
           
        }
        catch(NullPointerException e){
            
             OUDialog.alert("ERROR: No se a selecionado ningun archivo\n " + e);
        } catch (IOException ex) {
            OUDialog.alert("ERROR: Problema con acceso al sistema de archivos\n" + ex);
            
        }
       
   
        
    }
    
    
    /**
     * Escribir archivos.mod modificados (Datos Globales y MainPrg)
     * @param aList
     * @param bList 
     */
    public void writeModule(List <String> aList,List <String> bList){
            try {
                List <String> alist = aList;
                List <String> blist = bList;
                for(int i=0;i<alist.size();i++){
                    
                    if(aList.get(i).contains("DEFAULT:")){
                       
                        for(int j=0;j<blist.size();j++){
                            this.aFileWriter.write(blist.get(j));
                            this.aFileWriter.newLine();
                        }
                       
                        
                    }
                    
                    this.aFileWriter.write(alist.get(i)); 
                    this.aFileWriter.newLine();
                    if(aList.get(i).contains("M_DatosGlobalPrg")){
                        this.aFileWriter.write("  !Puntos NCV2");
                        this.aFileWriter.newLine();
                        for(int j=0;j<blist.size();j++){
                           
                            this.aFileWriter.write(blist.get(j));
                            this.aFileWriter.newLine();
                        }
                        
                        this.aFileWriter.newLine();
                        this.aFileWriter.write("  !Puntos VS20");
                        this.aFileWriter.newLine();
                        
                    }
                    
                }
        
            } catch (IOException ex) {
                OUDialog.alert("ERROR: Problema con acceso al sistema de archivos\n" + ex);
            }
            finally{
                try {
                   
                    this.aFileWriter.close(); 
                }   
                catch (IOException ex) {
                    OUDialog.alert("ERROR: Problema con acceso al sistema de archivos\n" + ex);
                }
            }
        
    }
    /**
     * Escribir archivos.mod modificados (archivos de rutinas de trabajo)
     * @param aList 
     */
    public void writeModuleVR(List <String> aList){
            try {
                List <String> alist = aList;
               
                
                for(int i=0;i<alist.size();i++){
                    
                    
                    this.aFileWriter.write(alist.get(i)); 
                    this.aFileWriter.newLine();
                    
                    if(aList.get(i).replaceAll(" ", "").startsWith("!Eliminado")){
                        
                        if(!aList.get(i+1).replaceAll(" ", "").startsWith("!Eliminado")){
                            this.aFileWriter.write("   !Revisar puntos de enlace"); 
                            this.aFileWriter.newLine();
                            this.aFileWriter.write("    MBPararPrograma;");
                            this.aFileWriter.newLine();
                            
                        }
                        
              
                    }  
                }
        
            } catch (IOException ex) {
                OUDialog.alert("ERROR: Problema con acceso al sistema de archivos\n" + ex);
            }
            finally{
                try {
                   
                    this.aFileWriter.close(); 
                }   
                catch (IOException ex) {
                    OUDialog.alert("ERROR: Problema con acceso al sistema de archivos\n" + ex);
                }
            }
        
    }
    
  
    
    /**
     * 
     * @return Retorna el nombre del archivo 
     * @see String
     */  
    public String getNombre(){
        String path;
        path = this.aFile.getName();
        return path;
    }
    
  
    
}
