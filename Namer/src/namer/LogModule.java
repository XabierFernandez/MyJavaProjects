/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package namer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ou.OUDialog;

/**
 *
 * @author Xabier
 */
public class LogModule {
    
    private String pathname;
    private File aFile;
    private BufferedWriter aFileWriter;
    
    public LogModule(String aPathName){
        
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
    
      public void writeString(String aString){
        try {
            this.aFileWriter.write(aString);
            this.aFileWriter.newLine();
        } catch (IOException ex) {
            Logger.getLogger(ModuleMod.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public void writeStringClose(){
        
        try {
                
                this.aFileWriter.close(); 
            }   
            catch (IOException ex) {
                OUDialog.alert("ERROR: Problema con acceso al sistema de archivos\n" + ex);
            }  
    }
    
}
