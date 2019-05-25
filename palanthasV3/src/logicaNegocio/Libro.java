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
public class Libro {
    
    int id;
    String titulo;
    String autor;
    String añoPublicacion;
    String genero;
    boolean prestable;
    int idLector;
    
    public Libro (String titulo, String autor, String añoPublicacion, String genero, boolean prestable) {
    
        this.titulo = titulo;
        this.autor = autor;
        this.añoPublicacion = añoPublicacion;
        this.genero = genero;
        this.prestable = prestable;
        this.idLector = 0;
    }
    
    public Libro () {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(String añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean getPrestable() {
        return prestable;
    }

    public void setPrestable(boolean prestable) {
        this.prestable = prestable;
    }

    public void setLector(int idLector){
        this.idLector = idLector;
    }
    
    public int getLector(){
        return idLector;
    }

    @Override   
    public String toString() {
        return "·ID: " + id + " | TITULO: " + titulo + " | AUTOR: " + autor + " | AÑO PUBLICACIÓN: " + añoPublicacion + " | GÉNERO: " + genero + " | PRESTABLE: " + prestable + " | ID LECTOR: " + idLector + " |";
    }
}
