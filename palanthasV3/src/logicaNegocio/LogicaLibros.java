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
    
    public boolean altaLibro (String prestable, String titulo, String autor, String añoPublicacion, String genero) {

    if (accesoDatosLibros.altaLibro(prestable, titulo, autor, añoPublicacion, genero)) return true;
    else return false;
    }
}
