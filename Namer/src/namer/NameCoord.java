/*
 * Clase coordinador perteneciente al paquete namer
 */
package namer;



import java.text.SimpleDateFormat;
import java.util.*;




/**
 *
 * @author Xabier
 */
public class NameCoord {
    
   
    private final List <Module> modulos;
    private final HojaCalc hojaexcel;
    private final String dirMod;
    private final LogModule logFile;
   
    private final int initDat;
    private final int finalDat;
    private final int fieldVS20;
    private final int fieldNCV2;
    
    private final int fieldX;
    private final int fieldY;
    private final int fieldZ;
    private String filename;
   
    
    /**
     * Constructor del objeto coordinador
     * @param listaModulos
     * @param aHojaExcel
     * @param aDirMod
     * @param aInitDat
     * @param aFinalDat
     * @param aFieldVS20
     * @param aFieldNCV2
     * @param aFieldX
     * @param aFieldY
     * @param aFieldZ 
     */
    public NameCoord(List <String> listaModulos, String aHojaExcel, String aDirMod,String aInitDat,String aFinalDat, 
            String aFieldVS20, String aFieldNCV2, String aFieldX, String aFieldY, String aFieldZ){
        
       this.modulos=new ArrayList<>();
       this.hojaexcel = new HojaCalc(aHojaExcel);
       this.logFile = new LogModule(aDirMod +"\\"+"LogFile.html");
       this.dirMod = aDirMod;
       this.initDat = Integer.parseInt(aInitDat);
       this.finalDat = Integer.parseInt(aFinalDat);
       this.fieldVS20 = Integer.parseInt(aFieldVS20);
       this.fieldNCV2 = Integer.parseInt(aFieldNCV2);
       
       this.fieldX=Integer.parseInt(aFieldX);
       this.fieldY=Integer.parseInt(aFieldY);
       this.fieldZ=Integer.parseInt(aFieldZ);
       this.filename=null;
       
       for(String item:listaModulos){
            
             this.modulos.add(new Module(item));
       }
    }
  
    
    /**
     * 
     * @return Retorna list de archivos.mod
     * @see List <Module> 
     */
    public List <Module> getModules(){
        
        return this.modulos;
    }
    /**
     * Metodo para procesar archivos .mod
     */
    public void proceder(){
        
        for (int i = 0;i<this.modulos.size();i++){
            String name = this.getNombre(i);
            
            if(name.equals("M_DatosGlobalPrg.mod")){
               
               this.doMDatosGlobal(i); 
            }
            
            if(name.equals("M_MainPrg.mod")){
                
               //this.doMainPrg(i);
            }
             
            if(name.contains("M_V")){
                
                this.doM_VR(i);
            }
               
        }
        
          
   }
    public void writeLogFile(String aString){
        
        this.logFile.writeString(aString);
    }
    
    public void writeLogFileClose(){
        
        this.logFile.writeStringClose();
    }
    
    
  /**
   * 
   * @param col
   * @param row
   * @return Retorna contenido de la celda(columna,fila)
   * @see String
   */   
  public String getCellTerm(int col,int row){
      
      return this.hojaexcel.getASheet(0).getCell(col, row).getContents();
  }  
  /**
   * 
   * @param pos numero indicando la posicion del archivo .mod 
   * @return Retorna el numero de lineas del archivo .mod
   * dentro de la lista 'modulos'
   * @see Integer
   */
  public int getNumLines(int pos){
      
      return this.modulos.get(pos).getNumLines();
      
  }
  /**
   * 
   * @param pos numero indicando la posicion del archivo .mod 
   * @return Retorna lista con tadas las lineas del archivo .mod
   * dentro de 'modulos'
   * @see List <String>
   */
  public List <String> getLista(int pos){
      
      return this.modulos.get(pos).getLista();
  }
  /**
   * 
   * @param pos numero indicando la posicion del archivo .mod 
   * @return Retorna el nombre del archivo en la posicion definida
   * por el parametro 'pos' dentro de la lista 'modulos'
   * @see String
   */
  public String getNombre(int pos){
        
        String path;
        path = this.modulos.get(pos).getNombre();
        return path;
    }
  
  public List <String> copyDatGlob(int pos ,String aTerm){
      
        List <String> copyList;
        copyList = new ArrayList<>();
        for(String linea: this.modulos.get(pos).getLista()){
            if(linea.contains(aTerm)){
                copyList.add(linea);
                
            }
        }
        
        return copyList ;
        
    }
  public static int getNumFromString(String aString){
      
        String string = aString.replaceAll(" ", "");
        int a;
        int b;
       
        a=string.indexOf("V");
        b=string.indexOf("H");
            
        
        return Integer.parseInt(string.substring((a+1), b));
        
    }
  public static int getNumFromString1(String aString){
      
        String string = aString.replaceAll(" ", "");
        int a;
        int b;
       
        a=string.indexOf("n");
        b=string.indexOf("H");
            
        
        return Integer.parseInt(string.substring((a+1), b));
        
    }
  public static int getNumFromStringCase(String aString){
      
        String string = aString.replaceAll(" ", "");
        int a;
        int b;
       
        a=string.indexOf("E");
        b=string.indexOf(":");
            
        
        return Integer.parseInt(string.substring((a+1), b));
        
    }
  public static String getSubStringGlobal(String aString, String bString){
      
        String string = aString;
        int a=0;
        int b=0;
        int countA=0;
        int countB=0;
        String result;
        for(int i=0;i<string.length();i++){
            
            char c = string.charAt(i);
            if(c=='['){
                countA++;
                if(countA==2){
                  a=i;   
                }  
            }
            if(c==','){
                countB++;
                if(countB==3){
                  b=i;   
                }   
            }
        }
       
              result = string.substring(0, (a+1)) + bString + string.substring((b-1), (string.length()));
       
        return result;
        
    }
   public static String getSubStringVR(String aString){
      
        String string = aString;
        int a;
        int b;
   
        String result;
        a=string.indexOf('R');
        b=string.indexOf(',');
        
       
        result = string.substring(a,b);
       
        return result;
        
    }
   public static String getSubStringGlobal2(String aString){
      
        String string = aString;
        int a;
        int b;
   
        String result;
        a=string.indexOf(':');
        b=string.indexOf(';');
       
       
        result = string.substring(a,b+1);
       
        return result;
        
    }
  
  
 public void doMainPrg(int aInt){
                    this.writeLogFile("<p>"+ getCurrentTimeStamp() + "<font color=\"green\">-- Procesando archivo " + this.dirMod + "\\" + this.getNombre(aInt)+"</p></font>");
                    List <String> tempLista2 = this.getLista(aInt);
                    List <String> newList2 = new ArrayList<>();
                    List <String> newList3 = new ArrayList<>();
                    int count;
                    int count1;
                    int num1;
                    for(int num = 0;num<this.getNumLines(aInt);num++){
                        
                        if(tempLista2.get(num).contains("CASE 1")){
                            num1=num;
                            while(!(tempLista2.get(num1).contains("DEFAULT:"))){
                                 newList2.add(tempLista2.get(num1));
                                num1++;
                            }
                        }
                    }     
                        for(String item:newList2){
                            
                            if(item.contains("CASE")){
                                count=getNumFromStringCase(item);
                                if(count<10){
                                    if((item.replaceAll(" ", "").startsWith("CASE"))){
                                        newList3.add(item.replace("CASE ", "CASE 50"));
                                        
                                    }
                                                
                                }
                                if(count>=10){
                                    if((item.replaceAll(" ", "").startsWith("CASE"))){
                                        newList3.add(item.replace("CASE ", "CASE 5"));
                                        
                                    }
                                }
                            }
                            if(item.contains("R_V")&& !item.contains("R_VComun")){
                                count1=getNumFromString(item);
                                if(count1<10){
                                    newList3.add("      MBProbarVariante;");
                                    newList3.add(item.replace("R_V", "R_V50"));         
                                }
                                if(count1>=10){
                                   newList3.add("      MBProbarVariante;");
                                   newList3.add(item.replace("R_V", "R_V5")); 
                                }
                            }
                            if(item.contains("R_VComun")){
                                count1=getNumFromString1(item);
                                if(count1<10){
                                    newList3.add("      MBProbarVariante;");
                                    newList3.add(item.replace("R_VComun", "R_VComun50"));         
                                }
                                if(count1>=10){
                                   newList3.add("      MBProbarVariante;");
                                   newList3.add(item.replace("R_VComun", "R_VComun5")); 
                                }
                            }
                            ////////////////////////////////////////////////
                            if((!item.contains("CASE"))&&(!item.contains("R_V"))){
                                 newList3.add(item);
                            }
                            
                        }
                        
                     
                    newList3.removeAll(Collections.singleton(null));
                    tempLista2.removeAll(Collections.singleton(null));
                    ModuleMod moduloMainPrg; 
                    moduloMainPrg = new ModuleMod(this.dirMod + "\\" + this.getNombre(aInt));
                    moduloMainPrg.writeModule(tempLista2,newList3);
                    this.writeLogFile("<p>"+getCurrentTimeStamp() + "<font color=\"blue\">-- Archivo procesado " + this.dirMod + "\\" + this.getNombre(aInt)+"</p></font>");
     
 }
 
 public void doMDatosGlobal(int aInt){
                        this.writeLogFile("<p>"+getCurrentTimeStamp() + "<font color=\"green\">-- Procesando archivo " + this.dirMod + "\\" + this.getNombre(aInt)+"</p></font>");
                        List <String> copyTempLista = this.copyDatGlob(aInt, "CONST robtarget");
                        List <String> tempLista1 = this.getLista(aInt);
                        List<String> newList1 = new ArrayList<>();
                     
                        boolean bOK;
                        boolean bOK1;
                        for(int k = 0;k<this.getNumLines(aInt);k++){
                            bOK=false;
                            bOK1=false;
                            for (int j=(this.initDat-1);j<this.finalDat;j++){ 
                                String aCellTerm = "RR" + this.getCellTerm((this.fieldNCV2-1), j) + ":=";
                                String bCellTerm = "RR" + this.getCellTerm((this.fieldVS20-1), j) + ":=";
                                String cCellTerm =(this.getCellTerm((this.fieldVS20-2), j));
                                
                                bCellTerm = bCellTerm.replace("_", "");
                                bCellTerm = bCellTerm.replace("Y", "");
                                
                                String xCellTerm =(this.getCellTerm((this.fieldX-1), j));
                                String yCellTerm =(this.getCellTerm((this.fieldY-1), j));
                                String zCellTerm =(this.getCellTerm((this.fieldZ-1), j));
                                String puntoCoord = xCellTerm.replaceAll(",", "\\.") + ',' + yCellTerm.replaceAll(",", "\\.") + ',' + zCellTerm.replaceAll(",", "\\.");
                                
                                
                                
                                if (tempLista1.get(k).contains(aCellTerm) && tempLista1.get(k).contains("CONST robtarget")){
                                    
                                    switch (cCellTerm){
                                        case "12":
                                            //String string12=getSubStringGlobal(tempLista1.get(k), puntoCoord);
                                            //newList1.add(string12.replace(aCellTerm, bCellTerm));
                                            newList1.add("  !Eliminado cod. 12 " + tempLista1.get(k));
                                            this.writeLogFile(("<p>"+getCurrentTimeStamp() + "<font color=\"red\"><b>-- Punto eliminado cod.12" + tempLista1.get(k)+"</b></p></font>"));
                                            //newList1.add(tempLista1.get(k).replace(aCellTerm, bCellTerm));
                                            bOK=true;
                                            bOK1=true;
                                            break;
                                        case "9":
                                            newList1.add("  !Eliminado cod. 9 " + tempLista1.get(k));
                                            this.writeLogFile(("<p>"+getCurrentTimeStamp() + "<font color=\"red\"><b>-- Punto eliminado cod.9" + tempLista1.get(k)+"</b></p></font>"));
                                            bOK=true;
                                            bOK1=true;
                                            break;
                                        case "2":
                                            newList1.add(tempLista1.get(k).replace(aCellTerm, bCellTerm));
                                            bOK=true;
                                            bOK1=true;
                                            break;
                                        case "3":
                                            newList1.add(tempLista1.get(k).replace(aCellTerm, bCellTerm));
                                            bOK=true;
                                            bOK1=true;
                                            break;
                                        case "8":
                                            String string8=getSubStringGlobal(tempLista1.get(k), puntoCoord);  
                                            newList1.add(string8.replace(aCellTerm, bCellTerm));
                                            bOK=true;
                                            bOK1=true;
                                            break;
                                        case "13":
                                            newList1.add(tempLista1.get(k).replace(aCellTerm, bCellTerm));
                                            bOK=true;
                                            bOK1=true;
                                            break;
                                        case "14":
                                            newList1.add("  !Eliminado cod. 14 " + tempLista1.get(k));
                                            this.writeLogFile(("<p>"+getCurrentTimeStamp() + "<font color=\"red\"><b>-- Punto eliminado cod.14" + tempLista1.get(k)+"</b></p></font>"));
                                            bOK=true;
                                            bOK1=true;
                                            break;
                                        case "15":
                                            String string15=getSubStringGlobal(tempLista1.get(k), puntoCoord);  
                                            newList1.add(string15.replace(aCellTerm, bCellTerm));
                                            bOK=true;
                                            bOK1=true;
                                            break;
                                        case "16":
                                            newList1.add("  !Eliminado cod. 16 " + tempLista1.get(k));
                                            this.writeLogFile(("<p>"+getCurrentTimeStamp() + "<font color=\"red\"><b>-- Punto eliminado cod.16" + tempLista1.get(k)+"</b></p></font>"));
                                            bOK=true;
                                            bOK1=true;
                                            break;
                                        default:
                                            
                                            break;
                                    } 
                                }
                                
                            }
                            if (tempLista1.get(k).contains("CONST robtarget")&& bOK1==false){
                                    newList1.add("  !No aparece en el archivo excel" + tempLista1.get(k));
                                    this.writeLogFile(("<p>"+getCurrentTimeStamp() + "<font color=\"red\"><b>-- No aparece en el archivo excel" + tempLista1.get(k)+"</b></p></font>"));
                                    bOK=true;                                    
                             }
                            if(bOK==false){
                                    newList1.add(tempLista1.get(k)); 
                            }
                                     
                        }
                        newList1.removeAll(Collections.singleton(null));
                        copyTempLista.removeAll(Collections.singleton(null));
                        ModuleMod moduloDatosGlobal; 
                        moduloDatosGlobal = new ModuleMod(this.dirMod + "\\" + this.getNombre(aInt));
                        moduloDatosGlobal.writeModule(newList1,copyTempLista);
                        this.writeLogFile("<p>"+getCurrentTimeStamp() + "<font color=\"blue\">-- Archivo procesado " + this.dirMod + "\\" + this.getNombre(aInt)+"</p></font>");
     
     
 }
 
 public void doM_VR(int aInt){
     
                    this.writeLogFile("<p>"+getCurrentTimeStamp() + "<font color=\"green\">-- Procesando archivo " + this.dirMod + "\\" + this.getNombre(aInt)+"</p></font>");
                    List <String> tempLista = this.getLista(aInt);
                    List <String> listatemp = new ArrayList<>();
                    List <String> listaFinal = new ArrayList<>();
                                    

                    
                    for(String item:tempLista){
                            int anum;
                            int anum1;
                            
                            if(item.replaceAll(" ","").startsWith("MODULE")&& (!item.contains("VComun"))){
                                anum = getNumFromString(item);
                                
                                if(anum<10){
                                    if((item.replaceAll(" ", "").startsWith("MODULE"))){
                                        listatemp.add(item.replaceFirst("M_V", "M_V50"));
                                        filename=this.getNombre(aInt).replace("M_V", "M_V50");
                                    }
                                                
                                }
                                if(anum>=10){
                                    if((item.replaceAll(" ", "").startsWith("MODULE"))){
                                        listatemp.add(item.replaceFirst("M_V", "M_V5"));
                                        filename=this.getNombre(aInt).replace("M_V", "M_V5");
                                    }
                                }
                            }
                            if(item.replaceAll(" ","").startsWith("MODULE")&& (item.contains("VComun"))){
                                anum = getNumFromString1(item);
                                
                                if(anum<10){
                                    if((item.replaceAll(" ", "").startsWith("MODULE"))){
                                        listatemp.add(item.replaceFirst("M_VComun", "M_VComun50"));
                                        filename=this.getNombre(aInt).replace("M_VComun", "M_VComun50");
                                    }
                                                
                                }
                                if(anum>=10){
                                    if((item.replaceAll(" ", "").startsWith("MODULE"))){
                                        listatemp.add(item.replaceFirst("M_VComun", "M_VComun5"));
                                        filename=this.getNombre(aInt).replace("M_VComun", "M_VComun5");
                                    }
                                }
                            }
                            
                            ///////////////////////////////////////////////////////////////
                            
                            
                            if(item.replaceAll(" ","").startsWith("PROC")&& (!item.contains("VComun"))){
                                anum1 = getNumFromString(item);
                                if(anum1<10){
                                    if((item.replaceAll(" ", "").startsWith("PROC"))){
                                        listatemp.add(item.replaceFirst("R_V", "R_V50"));
                                    }
                                }
                                if(anum1>=10){
                                   if((item.replaceAll(" ", "").startsWith("PROC"))){
                                        listatemp.add(item.replaceFirst("R_V", "R_V5"));
                                    }  
                                }
                            }
                            if(item.replaceAll(" ","").startsWith("PROC")&& (item.contains("VComun"))){
                                anum1 = getNumFromString1(item);
                                if(anum1<10){
                                    if((item.replaceAll(" ", "").startsWith("PROC"))){
                                        listatemp.add(item.replaceFirst("R_VComun", "R_VComun50"));
                                    }
                                }
                                if(anum1>=10){
                                   if((item.replaceAll(" ", "").startsWith("PROC"))){
                                        listatemp.add(item.replaceFirst("R_VComun", "R_VComun5"));
                                    }  
                                }
                            }
                            
                         ////////////////////////////////////////////                            
                            if((!item.replaceAll(" ", "").startsWith("MODULE"))&&(!item.replaceAll(" ", "").startsWith("PROC"))){
                                 listatemp.add(item);
                            }
                            
                            
                        }
                   
                    /////////////////////////////////////
                        boolean bOK;
                        for(int k = 0;k<listatemp.size();k++){
                            bOK=false;
                            
                            for (int j=(this.initDat-1);j<this.finalDat;j++){ 
                                String aCellTerm = "RR" + this.getCellTerm((this.fieldNCV2-1), j);
                                String bCellTerm = "RR" + this.getCellTerm((this.fieldVS20-1), j);
                                String cCellTerm =(this.getCellTerm((this.fieldVS20-2), j));
                                
                                bCellTerm = bCellTerm.replace("_", "");
                                bCellTerm = bCellTerm.replace("Y", "");
                                
                                if ((listatemp.get(k).contains(aCellTerm))){
                                    
                                    if(listatemp.get(k).contains("MoveJ RR")&& !listatemp.get(k).contains("MBSpotJ") ){  
                                      listaFinal.add("    !Revisar.Punto fuera de Standar" + listatemp.get(k).replace(aCellTerm, bCellTerm)); 
                                      this.writeLogFile(("<p>"+getCurrentTimeStamp() + "<font color=\"red\"><b>-- !Revisar.Punto fuera de Standar" + listatemp.get(k)+ "</b></p></font>"));
                                      bOK=true;
                                    }
                                    else{
                                    switch (cCellTerm){
                                        case "12":
                                            //listaFinal.add("    !Eliminado cod. 12 " + listatemp.get(k));
                                            //this.writeLogFile(("<p>"+getCurrentTimeStamp() + "<font color=\"red\"><b>-- Punto eliminado cod.12" + listatemp.get(k)+"</b></p></font>"));
                                            listaFinal.add(listatemp.get(k).replace(aCellTerm, bCellTerm));
                                            bOK=true;
                                            break;
                                        case "9":
                                            listaFinal.add("    !Eliminado cod. 9 " + listatemp.get(k));
                                            this.writeLogFile(("<p>"+getCurrentTimeStamp() + "<font color=\"red\"><b>-- Punto eliminado cod.9" + listatemp.get(k)+"</b></p></font>"));
                                            bOK=true;
                                            break;
                                        case "2":
                                            listaFinal.add(listatemp.get(k).replace(aCellTerm, bCellTerm));
                                            bOK=true;
                                            break;
                                        case "3":
                                            listaFinal.add(listatemp.get(k).replace(aCellTerm, bCellTerm));
                                            bOK=true;
                                            break;
                                        case "8":
                                            listaFinal.add(listatemp.get(k).replace(aCellTerm, bCellTerm));
                                            bOK=true;
                                            break;
                                        case "13":
                                            listaFinal.add(listatemp.get(k).replace(aCellTerm, bCellTerm));
                                            bOK=true;
                                            break;
                                        case "14":
                                            listaFinal.add("   !Eliminado cod. 14 " + listatemp.get(k));
                                            this.writeLogFile(("<p>"+getCurrentTimeStamp() + "<font color=\"red\"><b>-- Punto eliminado cod.14" + listatemp.get(k)+"</b></p></font>"));
                                            bOK=true;
                                            break;
                                        case "15":
                                            listaFinal.add(listatemp.get(k).replace(aCellTerm, bCellTerm));
                                            bOK=true;
                                            break;
                                        case "16":
                                            listaFinal.add("   !Eliminado cod. 16 " + listatemp.get(k));
                                            this.writeLogFile(("<p>"+getCurrentTimeStamp() + "<font color=\"red\"><b>-- Punto eliminado cod.16" + listatemp.get(k)+"</b></p></font>"));
                                            bOK=true;
                                            break;
                                        default:
                                            break;
                                    }
                                   }
                                }
                            
                               
                            }
                            if(bOK==false){
                               
                               listaFinal.add(listatemp.get(k));    
                                                             
                                
                            }
                                     
                        }
                    ////////////////////////////////////
                        
                    listaFinal.removeAll(Collections.singleton(null));
                    

                    ModuleMod moduloVR;
                    
                    moduloVR = new ModuleMod(this.dirMod + "\\" + filename);
                    moduloVR.writeModuleVR(listaFinal);
                    this.writeLogFile("<p>"+getCurrentTimeStamp() + "<font color=\"blue\">-- Archivo procesado " + this.dirMod + "\\" + this.getNombre(aInt)+"</p></font>");
                 
    


 
 }
 
  public static String getCurrentTimeStamp() {
    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//dd/MM/yyyy
    Date now = new Date();
    String strDate = sdfDate.format(now);
    return strDate;
}
 
 
 
}
