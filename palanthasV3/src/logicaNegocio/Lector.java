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
       
    public Lector (String nombre, String apellidos, String NIF, String telefono, String email) {
    
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.NIF = NIF;
        this.telefono = telefono;
        this.email = email;
    }
    
    public Lector () {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getValido() {
        return valido;
    }

    public void setValido(String valido) {
        this.valido = valido;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    @Override
    public String toString() {
        return "Lector{" + "valido=" + valido + ", nombre=" + nombre + ", apellidos=" + apellidos + " ,ID=" + ID + ", NIF=" + NIF + ", telefono=" + telefono + ", email=" + email + '}';
    }
}
