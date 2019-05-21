/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaNegocio;

/**
 *
 * @author dam08
 */
public class Lector {
    
    String valido;
    int ID;
    String nombre;
    String apellidos;
    String NIF;
    String telefono;
    String email;
    /*int idLibroPrestado1;
    int idLibroPrestado2;*/
       
    /**
     *
     * @param nombre
     * @param apellidos
     * @param NIF
     * @param telefono
     * @param email
     */
    public Lector (String nombre, String apellidos, String NIF, String telefono, String email) {
    
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.NIF = NIF;
        this.telefono = telefono;
        this.email = email;
        /*this.idLibroPrestado1 = 0;
        this.idLibroPrestado2 = 0;*/
    }
    
    /**
     *
     */
    public Lector () {}

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     *
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     *
     * @return
     */
    public String getNIF() {
        return NIF;
    }

    /**
     *
     * @param NIF
     */
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    /**
     *
     * @return
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getValido() {
        return valido;
    }

    /**
     *
     * @param valido
     */
    public void setValido(String valido) {
        this.valido = valido;
    }

    /**
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     *
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    
   /*public void setLibroPrestado1(int idLibro){
        this.idLibroPrestado1 = idLibro;
    }
    
    public int getLibroPrestado1(){
        return idLibroPrestado1;
    }
    
    public void setLibroPrestado2(int idLibro){
        this.idLibroPrestado2 = idLibro;
    }
    
    public int getLibroPrestado2(){
        return idLibroPrestado2;*/

    @Override
    public String toString() {
        return "Lector{" + "valido=" + valido + ", ID=" + ID + ", nombre=" + nombre + ", apellidos=" + apellidos + ", NIF=" + NIF + ", telefono=" + telefono + ", email=" + email + '}';
    }
    

    
    
    

}
