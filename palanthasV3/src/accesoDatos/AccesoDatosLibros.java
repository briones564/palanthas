/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesoDatos;

import static accesoDatos.accesoDatosLectores.borrarFichero;
import static accesoDatos.accesoDatosLectores.file;
import static accesoDatos.accesoDatosLectores.fileName;
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
import logicaNegocio.Libro;
import logicaNegocio.Utilidades;

/**
 *
 * @author dam08
 */
public class AccesoDatosLibros {
    
    static RandomAccessFile randomAccessFile = null;
    static int nextId = 1;
    static int id = nextId++;   
    static String fileName = "libros";
    static File file;
    
    public  AccesoDatosLibros(){
    
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
            bufferedReader = new BufferedReader(new FileReader("libros.txt"));
            
            //Extraer cada linea del archivo de texto para escribirla en el fichero de acceso aleatorio ajustando su tamaño.
            while (string != null) { 
                
                randomAccessFile.writeInt(id++);
                
                string = bufferedReader.readLine();  
                if (string == null) break;//En este momento el lector ha de salir del bucle, pues ha encontrado "null".
                
                randomAccessFile.writeChars(Utilidades.ajustarString(string, 26)); 
                randomAccessFile.writeChars(Utilidades.ajustarString(bufferedReader.readLine(), 30)); 
                randomAccessFile.writeChars(Utilidades.ajustarString(bufferedReader.readLine(), 8)); 
                randomAccessFile.writeChars(Utilidades.ajustarString(bufferedReader.readLine(), 25));
                randomAccessFile.writeChar(bufferedReader.read());
                string = bufferedReader.readLine();
                string = bufferedReader.readLine();
            } 
            
        } catch (FileNotFoundException fileEx) {
        
            System.out.println(fileEx.getMessage());
            
        } catch (IOException ioEx) {
        
            System.out.println(ioEx.getMessage());
        }
    }
    
    public static ArrayList<Libro> getLibros () {
            
        //Crear objeto Lector para asignarle los datos ajustados extraídos del fichero de acceso aleatorio.
        Libro libro;
        ArrayList<Libro> libros = new ArrayList<Libro>();
        
        int posicion = 0;
        
        try {

            randomAccessFile.seek(posicion);
            
            //Bucle que crea lectores para visualizarlos (mientras haya datos en el fichero).
            for (;;) {
                libro = new Libro();
                libro.setId(randomAccessFile.readInt());
                //randomAccessFile.seek(randomAccessFile.getFilePointer());             
                libro.setTitulo((leerString(randomAccessFile, 26)).trim());
                libro.setAutor((leerString(randomAccessFile, 30)).trim());
                libro.setAñoPublicacion((leerString(randomAccessFile, 8)).trim());
                libro.setGenero((leerString(randomAccessFile, 25)).trim());
                libro.setPrestable(randomAccessFile.readChar());
                libros.add(libro);
               // System.out.println(lector.toString());
            }     
        } catch (EOFException endEx) {
        
            //System.out.println("Fin del fichero.");
            
        } catch (IOException ioEx) {
        
            System.out.println(ioEx.getMessage());
        }
        return libros;
    }
    
    private static String leerString (RandomAccessFile randomAccessFile, int longitud) throws IOException {
    
        char[] chars = new char[longitud];
        
        for (int i = 0; i < longitud; i++) {
        
            chars[i] = randomAccessFile.readChar();
        }     
        return new String(chars);
    }
    
    public static boolean altaLibro (String titulo, String autor, String añoPublicacion, String genero, char prestable) {
    
        //Buscar lector con atributo valido = 0 para escribir en su posición. Si no lo hay, escribirá al final del fichero.(Metodo?)
        //Crear objeto Lector para asignarle los datos ajustados extraídos del fichero de acceso aleatorio.
        Libro libro = new Libro();
        
        int posicion = 0;
        int alta = 0;
        
        try {

            randomAccessFile.seek(randomAccessFile.length());            
            randomAccessFile.writeInt(id++);
            randomAccessFile.writeChars(Utilidades.ajustarString(titulo, 26));
            randomAccessFile.writeChars(Utilidades.ajustarString(autor, 30));
            randomAccessFile.writeChars(Utilidades.ajustarString(añoPublicacion, 8));
            randomAccessFile.writeChars(Utilidades.ajustarString(genero, 25));
            randomAccessFile.writeChar(prestable);          

        } catch (EOFException endEx) {
        
            System.out.println("Fin del fichero.");
            
        } catch (IOException ioEx) {
        
            System.out.println(ioEx.getMessage());
        }
        return true;
    } 
    
   
    /*public static boolean modificarLector(String nombreOriginal, String nombreNuevo, String apellidos, String NIF, String telefono, String email) {
    
        //Crear objeto Lector para asignarle los datos ajustados extraídos del fichero de acceso aleatorio.
        Libro libro = new Libro();
        
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
    }*/
    
     public static void borrarFichero(){
        File file = new File(fileName); 
        
        if (file.exists()) {
            System.out.println("El fichero existe");
            try {
                randomAccessFile.close();
            } catch (IOException ex) {
                Logger.getLogger(AccesoDatosLibros.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (file.delete()) {
                System.out.println("Fichero borrado");
                // Si el fichero ha sido borrado se debe volver a crear el objeto
                // RandomAccessFile
                try {
                    randomAccessFile = new RandomAccessFile(fileName, "rw");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AccesoDatosLibros.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else System.out.println("Fichero NO borrado");
        }
        else {
            System.out.println("El fichero NO existe");
        }
    }
}

