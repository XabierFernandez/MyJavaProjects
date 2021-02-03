/*
 * Clase Module perteneciente al paquete namer
 */
package namer;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ou.OUDialog;


/**
 *
 * @author Xabier
 */
public class Module {
    
    private String pathname;
    private File aFile;
    private BufferedReader aFileReader;
    private List <String> lista;
    private String currentLine;
    /**
     * Constructor del objeto Module
     * @param aPathName ruta del archivo .mod 
     * 
     */
    public Module(String aPathName){
        
              
        try{
            this.currentLine= null;
            this.lista = new ArrayList <>();
            this.pathname = aPathName;
            this.aFile = new File(this.pathname);
            
        }
        catch(NullPointerException e){
            
             OUDialog.alert("ERROR: No se a selecionado ningun archivo\n " + e);
        } 
     
    }
    /**
     * 
     * @return Retorna el numero de lineas del archivo
     * @see Integer
     */
    public int getNumLines(){
          
        return this.getLista().size();
         
    }
    /**
     * 
     * @param aOcurr cadena de texto
     * @return Retorna el numero de veces que aparece la cadena definida por el parametro aOcurr
     * @see Integer
     */
    public int countOccur(String aOcurr){
        int count;
        count = 0;
        int lines = this.getNumLines();
        
            for(int i=0;i<lines;i++){
                if((this.getLista().get(i).contains(aOcurr))&& (this.getLista().get(i).replaceAll(" ", "").startsWith(aOcurr))){
                    count ++;
                }
            }
        
        return count;
        
    }
    /**
     * 
     * @return Retorna un Array con todas las lineas del archivo
     * @see List <String>
     */
    public List<String> getLista(){
       lista.clear();
        try{
            this.aFileReader = new BufferedReader(new FileReader(this.aFile));
            currentLine = this.aFileReader.readLine();
            while(currentLine !=null){
                this.lista.add(currentLine);
                currentLine = this.aFileReader.readLine();
               
            }
          
        }
        catch(IOException e){
        
                OUDialog.alert("Error getList(): al cerrar archivo\n" + e);
        }
        try {
            this.aFileReader.close(); 
        } catch (IOException ex) {
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        lista.removeAll(Collections.singleton(null));
        return lista;
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
