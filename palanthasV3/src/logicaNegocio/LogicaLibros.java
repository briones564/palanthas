/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocio;

import accesoDatos.AccesoDatosLibros;

/**
 *
 * @author dam08
 */
public class LogicaLibros {
    
    public static AccesoDatosLibros accesoDatosLibros;
    
    public LogicaLibros () {
    
        accesoDatosLibros = new AccesoDatosLibros();
    }
    
    public void reset () {
    
        accesoDatosLibros.reset();
    }
    
    public String visualizarLibros () {
        
        String libros = "";
        
        for (int i = 0; i < accesoDatosLibros.getLibros().size(); i++){
            
             libros += accesoDatosLibros.getLibros().get(i).toString() + "\n";
        }
        return libros;
    }
    
    public boolean altaLibro (String titulo, String autor, String añoPublicacion, String genero, boolean prestable) {

        if (accesoDatosLibros.altaLibro(titulo, autor, añoPublicacion, genero, prestable)){
            
            return true;
        }
        else return false;
    }
    
    public boolean modificarLibro (String tituloOriginal, String tituloNuevo, String autor, String añoPublicacion, String genero, boolean prestable) {
    
        if (accesoDatosLibros.modificarLibro(tituloOriginal, tituloNuevo, autor, añoPublicacion, genero, prestable)){
            
            return true;
        }
        else return false;
    }
    
    public Libro buscarTitulo(String titulo) {
        
        for (int i = 0; i < accesoDatosLibros.getLibros().size(); i++){
             
            if (accesoDatosLibros.getLibros().get(i).getTitulo().equalsIgnoreCase(titulo)) {
            
                return accesoDatosLibros.getLibros().get(i);
            }
        }
        return null;
    }
    
    public Libro buscarID (int id) {
    
        for (int i = 0; i < accesoDatosLibros.getLibros().size(); i++){
             
            if (accesoDatosLibros.getLibros().get(i).getId()== id) {
            
                return accesoDatosLibros.getLibros().get(i);
            }
        }
        return null;
    }
    
}
