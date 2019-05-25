package logicaNegocio;

import accesoDatos.AccesoDatosLectores;
/**
 *
 * @author dam08
 */
public class LogicaLectores {          

    public static AccesoDatosLectores accesoDatosLectores;
    
    public LogicaLectores() {
    
        accesoDatosLectores = new AccesoDatosLectores();
    }

    public void reset () {
    
        accesoDatosLectores.reset();
    }
    
    public String visualizarLectores () {
        
        String lectores = "";
        
        for (int i = 0; i < accesoDatosLectores.getLectores().size(); i++){
            
             lectores += accesoDatosLectores.getLectores().get(i).toString() + "\n";
        }
        return lectores;
    }
    
    public boolean altaLector (String nombre, String apellidos, String nif, String telefono, String email) {
    
        if (accesoDatosLectores.altaLector(nombre, apellidos, nif, telefono, email)) {
            
            return true;
        }
        else return false;
    }
    
    public boolean bajaLector (int id) {
        
        for (int i = 0; i < accesoDatosLectores.getLectores().size(); i++){
             
            if (accesoDatosLectores.getLectores().get(i).getID()== id) {
             
                accesoDatosLectores.bajaLector(id);
                return true;
            }
        }
        return false;
    }
    
    public boolean modificarLector (String nombreOriginal, String nombreNuevo, String apellidos, String NIF, String telefono, String email) {
    
        if (accesoDatosLectores.modificarLector(nombreOriginal, nombreNuevo, apellidos, NIF, telefono,email)) {
            return true;
        }
        else return false;
    }
    
    public Lector buscarNIF(String nif) {
        
        for (int i = 0; i < accesoDatosLectores.getLectores().size(); i++){
             
            if (accesoDatosLectores.getLectores().get(i).getNIF().equalsIgnoreCase(nif)) {
            
                return accesoDatosLectores.getLectores().get(i);
            }
        }
        return null;
    }
    
    public Lector buscarID (int id) {
    
        for (int i = 0; i < accesoDatosLectores.getLectores().size(); i++){
             
            if (accesoDatosLectores.getLectores().get(i).getID()== id) {
            
                return accesoDatosLectores.getLectores().get(i);
            }
        }
        return null;
    }
    
}
