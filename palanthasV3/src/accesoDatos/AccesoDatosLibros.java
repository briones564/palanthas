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
                
                
                
                string = bufferedReader.readLine();  
                if (string == null) break;//En este momento el lector ha de salir del bucle, pues ha encontrado "null".
                randomAccessFile.writeInt(id++);
                randomAccessFile.writeChars(Utilidades.ajustarString(string, 26)); 
                randomAccessFile.writeChars(Utilidades.ajustarString(bufferedReader.readLine(), 30)); 
                randomAccessFile.writeChars(Utilidades.ajustarString(bufferedReader.readLine(), 8)); 
                randomAccessFile.writeChars(Utilidades.ajustarString(bufferedReader.readLine(), 25));
                randomAccessFile.writeBoolean(Boolean.valueOf(Utilidades.ajustarString(bufferedReader.readLine(), 4)));
                randomAccessFile.writeInt(0);
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
                libro.setTitulo((Utilidades.leerString(randomAccessFile, 26)).trim());
                libro.setAutor((Utilidades.leerString(randomAccessFile, 30)).trim());
                libro.setAñoPublicacion((Utilidades.leerString(randomAccessFile, 8)).trim());
                libro.setGenero((Utilidades.leerString(randomAccessFile, 25)).trim());
                libro.setPrestable(randomAccessFile.readBoolean());
                libro.setLector(randomAccessFile.readInt());
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
     
    public static boolean altaLibro (String titulo, String autor, String añoPublicacion, String genero, boolean prestable) {
    
        //Buscar lector con atributo valido = 0 para escribir en su posición. Si no lo hay, escribirá al final del fichero.(Metodo?)
        //Crear objeto Lector para asignarle los datos ajustados extraídos del fichero de acceso aleatorio.

        Libro libro = new Libro();
        
        try {

            randomAccessFile.seek(randomAccessFile.length());   
            randomAccessFile.writeInt(id++);
            randomAccessFile.writeChars(Utilidades.ajustarString(titulo, 26));
            randomAccessFile.writeChars(Utilidades.ajustarString(autor, 30));
            randomAccessFile.writeChars(Utilidades.ajustarString(añoPublicacion, 8));
            randomAccessFile.writeChars(Utilidades.ajustarString(genero, 25));   
            randomAccessFile.writeBoolean(prestable);  
            randomAccessFile.writeInt(0);

        } catch (EOFException endEx) {
        
            System.out.println("Fin del fichero.");
            
        } catch (IOException ioEx) {
        
            System.out.println(ioEx.getMessage());
        }
        return true;
    }  
   
    public static boolean modificarLibro(String tituloOriginal, String tituloNuevo, String autor, String añoPublicacion, String genero, boolean prestable) {
    
        //Crear objeto Lector para asignarle los datos ajustados extraídos del fichero de acceso aleatorio.
        Libro libro = new Libro();
        
        int posicion = 0;
        
        try {

            randomAccessFile.seek(posicion);
            
            //Bucle que crea lectores para visualizarlos (mientras haya datos en el fichero).
            for (;;) {
                
                libro.setId(randomAccessFile.readInt());
                libro.setTitulo((Utilidades.leerString(randomAccessFile, 26)).trim());
                
                if (tituloOriginal.equalsIgnoreCase(libro.getTitulo())){                      
                
                    randomAccessFile.seek((randomAccessFile.getFilePointer())-52);
                    randomAccessFile.writeChars(Utilidades.ajustarString(tituloNuevo, 26));
                    randomAccessFile.writeChars(Utilidades.ajustarString(autor, 30));
                    randomAccessFile.writeChars(Utilidades.ajustarString(añoPublicacion, 8));
                    randomAccessFile.writeChars(Utilidades.ajustarString(genero, 25));    
                    randomAccessFile.writeBoolean(prestable); 
                    randomAccessFile.writeInt(libro.getLector());
                    break;
                }               
                libro.setAutor((Utilidades.leerString(randomAccessFile, 30)).trim());
                libro.setAñoPublicacion((Utilidades.leerString(randomAccessFile, 8)).trim());
                libro.setGenero((Utilidades.leerString(randomAccessFile, 25)).trim());
                libro.setPrestable(randomAccessFile.readBoolean());      
                libro.setLector(randomAccessFile.readInt());
            } 
            return true;
            
        } catch (EOFException endEx) {
        
            System.out.println("Fin del fichero.");
            
        } catch (IOException ioEx) {
        
            System.out.println(ioEx.getMessage());
        }
        return false;
    }

    public static void buscarTitulo (String titulo) {
    
        //Crear objeto Lector para asignarle los datos ajustados extraídos del fichero de acceso aleatorio.
        Libro libro = new Libro();
        
        int posicion = 0;
 
        try {

            randomAccessFile.seek(posicion);
            
            //Bucle que crea lectores para visualizarlos (mientras haya datos en el fichero).
            for (;;) {
                
                libro.setId(randomAccessFile.readInt());          
                libro.setTitulo((Utilidades.leerString(randomAccessFile, 26)).trim());
                libro.setAutor((Utilidades.leerString(randomAccessFile, 30)).trim());
                libro.setAñoPublicacion((Utilidades.leerString(randomAccessFile, 8)).trim());
                libro.setGenero((Utilidades.leerString(randomAccessFile, 25)).trim());
                libro.setPrestable(Boolean.valueOf((Utilidades.leerString(randomAccessFile, 4)).trim()));
                libro.setLector(randomAccessFile.readInt());
                
                if (titulo.equalsIgnoreCase(libro.getTitulo())){    
                   
                    System.out.println(libro.toString());
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
        Libro libro = new Libro();
        
        int posicion = 0;
        String string;
      
        try {

            randomAccessFile.seek(posicion);
            
            //Bucle que crea lectores para visualizarlos (mientras haya datos en el fichero).
            for (;;) {
                
                libro.setId(randomAccessFile.readInt());          
                libro.setTitulo((Utilidades.leerString(randomAccessFile, 26)).trim());
                libro.setAutor((Utilidades.leerString(randomAccessFile, 30)).trim());
                libro.setAñoPublicacion((Utilidades.leerString(randomAccessFile, 8)).trim());
                libro.setGenero((Utilidades.leerString(randomAccessFile, 25)).trim());
                libro.setPrestable(randomAccessFile.readBoolean());
                libro.setLector(randomAccessFile.readInt());
                
                if (ID == (libro.getId())){    
                   
                    System.out.println(libro.toString());
                    break;
                }                               
            }            
        } catch (EOFException endEx) {

            System.out.println("Fin del fichero.");

        } catch (IOException ioEx) {

            System.out.println(ioEx.getMessage());
        }
    }
    
    public static boolean prestar(int idLector, int idLibro){
    
        Libro libro;
        
        int posicion = 0;
        int prestamos = 0;
        
        try {
            
            randomAccessFile.seek(posicion);
            
            for (;;) {
                
                libro = new Libro();
                libro.setId(randomAccessFile.readInt());          
                libro.setTitulo((Utilidades.leerString(randomAccessFile, 26)).trim());
                libro.setAutor((Utilidades.leerString(randomAccessFile, 30)).trim());
                libro.setAñoPublicacion((Utilidades.leerString(randomAccessFile, 8)).trim());
                libro.setGenero((Utilidades.leerString(randomAccessFile, 25)).trim());
                libro.setPrestable(randomAccessFile.readBoolean());
                libro.setLector(randomAccessFile.readInt());
                
                if (libro.getLector() == idLector) {
                
                    prestamos++;
                }
                if (idLibro == (libro.getId())){    
                    
                    break;
                }                               
            }              
            if (libro.getPrestable() && libro.getLector() == 0 && prestamos < 2){
                
                randomAccessFile.seek(randomAccessFile.getFilePointer()-4);
                randomAccessFile.writeInt(idLector);     
                return true;
            }
            else {

                return false;
            }
            
        } catch (EOFException endEx) {
        
            System.out.println("Fin del fichero.");
            
        } catch (IOException ioEx) {
        
            System.out.println(ioEx.getMessage());
        }
        return false;
    }
    
    public static boolean devolver(int idLibro){
    
        Libro libro;
        
        int posicion = 0;
        
        try {

            randomAccessFile.seek(posicion);
            
            for (;;) {
                
                libro = new Libro();
                libro.setId(randomAccessFile.readInt());          
                libro.setTitulo((Utilidades.leerString(randomAccessFile, 26)).trim());
                libro.setAutor((Utilidades.leerString(randomAccessFile, 30)).trim());
                libro.setAñoPublicacion((Utilidades.leerString(randomAccessFile, 8)).trim());
                libro.setGenero((Utilidades.leerString(randomAccessFile, 25)).trim());
                libro.setPrestable(randomAccessFile.readBoolean());
                libro.setLector(randomAccessFile.readInt());
                
                if (idLibro == (libro.getId()) && libro.getLector() != 0){    
                    
                    randomAccessFile.seek(randomAccessFile.getFilePointer()-4);
                    randomAccessFile.writeInt(0);           
                    return true;
                }                                                
                else if (libro.getId()!= idLibro) {
            
                    System.out.println("LIBRO NO PRESTADO");
                    return false;
                }
            }                     
        } catch (EOFException endEx) {
        
            System.out.println("Fin del fichero.");
            
        } catch (IOException ioEx) {
        
            System.out.println(ioEx.getMessage());
        }
        return false;
    }
    
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

