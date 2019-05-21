package logicaNegocio;

import accesoDatos.accesoDatosLectores;
/**
 *
 * @author dam08
 */
public class LogicaLectores {          

    public static accesoDatosLectores accesoDatos;
    
    public LogicaLectores() {
    
        accesoDatos = new accesoDatosLectores();
    }

    public void reset () {
    
        accesoDatos.reset();
    }
    
    public String visualizarLectores () {
        
        String lectores = "";
        
        for (int i = 0; i < accesoDatos.getLectores().size(); i++){
            
             lectores += accesoDatos.getLectores().get(i).toString() + "\n";
        }
        return lectores;
    }
    
    public boolean altaLector (String nombre, String apellidos, String nif, String telefono, String email) {
    
        if (accesoDatos.altaLector(nombre, apellidos, nif, telefono, email)) {
            
            return true;
        }
        else return false;
    }
    
    public boolean bajaLector (int id) {
        
        for (int i = 0; i < accesoDatos.getLectores().size(); i++){
             
            if (accesoDatos.getLectores().get(i).getID()== id) {
             
                accesoDatos.bajaLector(id);
                return true;
            }
        }
        return false;
    }
    
    public boolean modificarLector (String nombreOriginal, String nombreNuevo, String apellidos, String NIF, String telefono, String email) {
    
        if (accesoDatos.modificarLector(nombreOriginal, nombreNuevo, apellidos, NIF, telefono,email)) {
            return true;
        }
        else return false;
    }
    
    public Lector buscarNIF(String nif) {
        
        for (int i = 0; i < accesoDatos.getLectores().size(); i++){
             
            if (accesoDatos.getLectores().get(i).getNIF().equalsIgnoreCase(nif)) {
            
                return accesoDatos.getLectores().get(i);
            }
        }
        return null;
    }
    
    public Lector buscarID (int id) {
    
        for (int i = 0; i < accesoDatos.getLectores().size(); i++){
             
            if (accesoDatos.getLectores().get(i).getID()== id) {
            
                return accesoDatos.getLectores().get(i);
            }
        }
        return null;
    }
    
}
