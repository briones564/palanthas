/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoDatos;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocio.Lector;
import logicaNegocio.Utilidades;

/**
 *
 * @author Stevie
 */
public class accesoDatosLectores {
    
    static RandomAccessFile randomAccessFile = null;
    static int nextId = 1;
    static int id = nextId++;   
    static String fileName = "lectores";
    static File file;
    
    public  accesoDatosLectores(){
    
        try {
            
            file = new File(fileName);
            randomAccessFile = new RandomAccessFile(file, "rw");// Apertura del fichero con permisos de LECTURA y ESCRITURA
            
        } catch (FileNotFoundException ex) {
            
            System.out.println(ex.getMessage());
        }
    }
    
    //Método para resetear ficheros: genera una cantidad determinada de datos binarios extraídos del archivo de texto.
    public static void reset () {
    
        BufferedReader bufferedReader;
        borrarFichero();
        
        try {

            String string = "";
            bufferedReader = new BufferedReader(new FileReader("lectores.txt"));
            
            //Extraer cada linea del archivo de texto para escribirla en el fichero de acceso aleatorio ajustando su tamaño.
            while (string != null) { 
                
                randomAccessFile.writeChars("1");//Escribe "1" al principio del objeto para identificarlo como válido.
                
                string = bufferedReader.readLine();  
                if (string == null) break;//En este momento el lector ha de salir del bucle, pues ha encontrado "null".
                
                randomAccessFile.writeChars(Utilidades.ajustarString(string, 16));

                string = bufferedReader.readLine();  
                randomAccessFile.writeChars(Utilidades.ajustarString(string, 20));

                randomAccessFile.writeInt(id++);
                
                string = bufferedReader.readLine();  
                randomAccessFile.writeChars(Utilidades.ajustarString(string, 9));

                string = bufferedReader.readLine();  
                randomAccessFile.writeChars(Utilidades.ajustarString(string, 9));

                string = bufferedReader.readLine();  
                randomAccessFile.writeChars(Utilidades.ajustarString(string, 16));
                
                string = bufferedReader.readLine();
                randomAccessFile.writeChars(Utilidades.ajustarString(string, 0));  
            } 
            
        } catch (FileNotFoundException fileEx) {
        
            System.out.println(fileEx.getMessage());
            
        } catch (IOException ioEx) {
        
            System.out.println(ioEx.getMessage());
        }
    }
    
    public static ArrayList<Lector> getLectores () {
            
        //Crear objeto Lector para asignarle los datos ajustados extraídos del fichero de acceso aleatorio.
        Lector lector;
        ArrayList<Lector> lectores = new ArrayList<Lector>();
        
        int posicion = 0;
        
        try {

            randomAccessFile.seek(posicion);
            
            //Bucle que crea lectores para visualizarlos (mientras haya datos en el fichero).
            for (;;) {
                lector = new Lector();
                lector.setValido((leerString(randomAccessFile, 1)).trim());
                lector.setNombre((leerString(randomAccessFile, 16)).trim());
                lector.setApellidos((leerString(randomAccessFile, 20)).trim());
                lector.setID(randomAccessFile.readInt());
                lector.setNIF((leerString(randomAccessFile, 9)).trim());
                lector.setTelefono((leerString(randomAccessFile, 9)).trim());
                lector.setEmail((leerString(randomAccessFile, 16)).trim());
                
                lectores.add(lector);
               // System.out.println(lector.toString());
            }     
        } catch (EOFException endEx) {
        
            //System.out.println("Fin del fichero.");
            
        } catch (IOException ioEx) {
        
            System.out.println(ioEx.getMessage());
        }
        return lectores;
    }
    
    private static String leerString (RandomAccessFile randomAccessFile, int longitud) throws IOException {
    
        char[] chars = new char[longitud];
        
        for (int i = 0; i < longitud; i++) {
        
            chars[i] = randomAccessFile.readChar();
        }     
        return new String(chars);
    }
    
    public static boolean altaLector (String nombre, String apellidos, String NIF, String Telefono, String Email) {
    
        //Buscar lector con atributo valido = 0 para escribir en su posición. Si no lo hay, escribirá al final del fichero.(Metodo?)
        //Crear objeto Lector para asignarle los datos ajustados extraídos del fichero de acceso aleatorio.
        Lector lector = new Lector();
        
        int posicion = 0;
        int alta = 0;
        
        try {

            randomAccessFile.seek(posicion);
            
            //Bucle que crea lectores para visualizarlos (mientras haya datos en el fichero).
            for (;;) {
                
                if (randomAccessFile.getFilePointer() == randomAccessFile.length()){
                    
                    lector.setNombre(nombre);
                    lector.setApellidos(apellidos);
                    lector.setID(id++);
                    lector.setNIF(NIF);
                    lector.setTelefono(Telefono);
                    lector.setEmail(Email);
                    lector.setValido("1");
                    randomAccessFile.writeChars("1");           
                    randomAccessFile.writeChars(Utilidades.ajustarString(lector.getNombre(), 16));
                    randomAccessFile.writeChars(Utilidades.ajustarString(lector.getApellidos(), 20));
                    randomAccessFile.writeInt(lector.getID());             
                    randomAccessFile.writeChars(Utilidades.ajustarString(lector.getNIF(), 9));               
                    randomAccessFile.writeChars(Utilidades.ajustarString(lector.getTelefono(), 9));               
                    randomAccessFile.writeChars(Utilidades.ajustarString(lector.getEmail(), 16)); 
                    break;
                }   
                
                lector.setValido((leerString(randomAccessFile, 1)).trim());
            
                if ("0".equals(lector.getValido()) || randomAccessFile.getFilePointer() == randomAccessFile.length()){
                    lector.setNombre(nombre);
                    lector.setApellidos(apellidos);
                    lector.setID(id++);
                    lector.setNIF(NIF);
                    lector.setTelefono(Telefono);
                    lector.setEmail(Email);
                    lector.setValido("1");
                    randomAccessFile.seek((randomAccessFile.getFilePointer())-2);     
                    randomAccessFile.writeChars("1");           
                    randomAccessFile.writeChars(Utilidades.ajustarString(lector.getNombre(), 16));
                    randomAccessFile.writeChars(Utilidades.ajustarString(lector.getApellidos(), 20));
                    randomAccessFile.writeInt(lector.getID());             
                    randomAccessFile.writeChars(Utilidades.ajustarString(lector.getNIF(), 9));               
                    randomAccessFile.writeChars(Utilidades.ajustarString(lector.getTelefono(), 9));               
                    randomAccessFile.writeChars(Utilidades.ajustarString(lector.getEmail(), 16)); 
                    break;
                }
                
                else {
                    lector.setNombre((leerString(randomAccessFile, 16)).trim());
                    lector.setApellidos((leerString(randomAccessFile, 20)).trim());
                    lector.setID((randomAccessFile.readInt()));
                    lector.setNIF((leerString(randomAccessFile, 9)).trim());
                    lector.setTelefono((leerString(randomAccessFile, 9)).trim());
                    lector.setEmail((leerString(randomAccessFile, 16)).trim());
                }
            } 
            return true;
            
        } catch (EOFException endEx) {
        
            System.out.println("Fin del fichero.");
            
        } catch (IOException ioEx) {
        
            System.out.println(ioEx.getMessage());
        }
        return false;
    } 
    
    public static boolean bajaLector(int ID) {
    
        //Buscar lector con atributo valido = 0 para escribir en su posición. Si no lo hay, escribirá al final del fichero.(Metodo?)
        //Crear objeto Lector para asignarle los datos ajustados extraídos del fichero de acceso aleatorio.
        Lector lector = new Lector();
        
        int posicion = 0;
        int baja = 0;
        String string;
        
        try {

            randomAccessFile.seek(posicion);
            
            //Bucle que crea lectores para visualizarlos (mientras haya datos en el fichero).
            for (;;) {
                
                lector.setValido((leerString(randomAccessFile, 1)).trim());
                lector.setNombre((leerString(randomAccessFile, 16).trim()));
                lector.setApellidos((leerString(randomAccessFile, 20).trim()));
                lector.setID(randomAccessFile.readInt());
                         
                if (ID == lector.getID()){    
                    
                    lector.setValido("0");
                    baja++;
                }
                if (baja == 1) {break;}
                
                lector.setNIF((leerString(randomAccessFile, 9)).trim());
                lector.setTelefono((leerString(randomAccessFile, 9)).trim());
                lector.setEmail((leerString(randomAccessFile, 16)).trim());                       
            } 
            if (baja == 1){
                
                randomAccessFile.seek((randomAccessFile.getFilePointer())-78);    
                randomAccessFile.writeChars("0"); 
                randomAccessFile.writeChars(Utilidades.ajustarString(lector.getNombre(), 16)); 
                randomAccessFile.writeChars(Utilidades.ajustarString(lector.getApellidos(), 20));
                randomAccessFile.writeInt(lector.getID());
                randomAccessFile.writeChars(Utilidades.ajustarString(lector.getNIF(), 9)); 
                randomAccessFile.writeChars(Utilidades.ajustarString(lector.getTelefono(), 9));
                randomAccessFile.writeChars(Utilidades.ajustarString(lector.getEmail(), 16));  
            }
            return true;
            
        } catch (EOFException endEx) {
        
            System.out.println("Fin del fichero.");
            
        } catch (IOException ioEx) {
        
            System.out.println(ioEx.getMessage());
        }
        return false;
    }
    
    public static boolean modificarLector(String nombreOriginal, String nombreNuevo, String apellidos, String NIF, String telefono, String email) {
    
        //Crear objeto Lector para asignarle los datos ajustados extraídos del fichero de acceso aleatorio.
        Lector lector = new Lector();
        
        int posicion = 0;
        int modificado = 0;
        String string;
        
        try {

            randomAccessFile.seek(posicion);
            
            //Bucle que crea lectores para visualizarlos (mientras haya datos en el fichero).
            for (;;) {
                
                lector.setValido((leerString(randomAccessFile, 1)).trim());
                lector.setNombre((leerString(randomAccessFile, 16).trim()));
                
                if (nombreOriginal.equals(lector.getNombre())){    
                   
                    lector.setApellidos((leerString(randomAccessFile, 20).trim()));
                    lector.setID(randomAccessFile.readInt()); 
                    modificado++;
                }
                if (modificado == 1) {break;}
                
                lector.setApellidos((leerString(randomAccessFile, 20).trim()));
                lector.setID(randomAccessFile.readInt()); 
                lector.setNIF((leerString(randomAccessFile, 9)).trim());
                lector.setTelefono((leerString(randomAccessFile, 9)).trim());
                lector.setEmail((leerString(randomAccessFile, 16)).trim());                       
            } 
            if (modificado == 1){
                
                randomAccessFile.seek((randomAccessFile.getFilePointer())-76);
                randomAccessFile.writeChars(Utilidades.ajustarString(nombreNuevo, 16));
                randomAccessFile.writeChars(Utilidades.ajustarString(apellidos, 20));
                randomAccessFile.writeInt(lector.getID());
                randomAccessFile.writeChars(Utilidades.ajustarString(NIF, 9));  
                randomAccessFile.writeChars(Utilidades.ajustarString(telefono, 9));
                randomAccessFile.writeChars(Utilidades.ajustarString(email, 16));  
            }
            return true;
            
        } catch (EOFException endEx) {
        
            System.out.println("Fin del fichero.");
            
        } catch (IOException ioEx) {
        
            System.out.println(ioEx.getMessage());
        }
        return false;
    }

    public static void buscarNIF (String NIF) {
    
        //Crear objeto Lector para asignarle los datos ajustados extraídos del fichero de acceso aleatorio.
        Lector lector = new Lector();
        
        int posicion = 0;
        int encontrado = 0;
        String string;
        
        try {

            randomAccessFile.seek(posicion);
            
            //Bucle que crea lectores para visualizarlos (mientras haya datos en el fichero).
            for (;;) {
                
                lector.setValido((leerString(randomAccessFile, 1)).trim());
                lector.setNombre((leerString(randomAccessFile, 16).trim()));                     
                lector.setApellidos((leerString(randomAccessFile, 20).trim()));
                lector.setID(randomAccessFile.readInt()); 
                lector.setNIF((leerString(randomAccessFile, 9)).trim());
                lector.setTelefono((leerString(randomAccessFile, 9)).trim());
                lector.setEmail((leerString(randomAccessFile, 16)).trim()); 
                
                if (NIF.equals(lector.getNIF())){    
                   
                    encontrado++;
                    System.out.println(lector.toString());
                    break;
                }                               
            }            
        } catch (EOFException endEx) {
        
            System.out.println("Fin del fichero.");
            
        } catch (IOException ioEx) {
        
            System.out.println(ioEx.getMessage());
        }
    }
    
    public static void buscarID (int ID) {

        //Crear objeto Lector para asignarle los datos ajustados extraídos del fichero de acceso aleatorio.
        Lector lector = new Lector();

        int posicion = 0;
        int encontrado = 0;
        String string;

        try {

            randomAccessFile.seek(posicion);

            //Bucle que crea lectores para visualizarlos (mientras haya datos en el fichero).
            for (;;) {

                lector.setValido((leerString(randomAccessFile, 1)).trim());
                lector.setNombre((leerString(randomAccessFile, 16).trim()));                     
                lector.setApellidos((leerString(randomAccessFile, 20).trim()));
                lector.setID(randomAccessFile.readInt()); 
                lector.setNIF((leerString(randomAccessFile, 9)).trim());
                lector.setTelefono((leerString(randomAccessFile, 9)).trim());
                lector.setEmail((leerString(randomAccessFile, 16)).trim()); 

                if (ID == lector.getID()){    

                    encontrado++;
                    System.out.println(lector.toString());
                    break;
                }                               
            }            
        } catch (EOFException endEx) {

            System.out.println("Fin del fichero.");

        } catch (IOException ioEx) {

            System.out.println(ioEx.getMessage());
        }
    }
    
    public static void borrarFichero(){
        File file = new File(fileName); 
        
        if (file.exists()) {
            System.out.println("El fichero existe");
            try {
                randomAccessFile.close();
            } catch (IOException ex) {
                Logger.getLogger(accesoDatosLectores.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (file.delete()) {
                System.out.println("Fichero borrado");
                // Si el fichero ha sido borrado se debe volver a crear el objeto
                // RandomAccessFile
                try {
                    randomAccessFile = new RandomAccessFile(fileName, "rw");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(accesoDatosLectores.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else System.out.println("Fichero NO borrado");
        }
        else {
            System.out.println("El fichero NO existe");
        }
    }
}