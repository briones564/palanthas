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
    int prestable;
    
    public Libro (String titulo, String autor, String añoPublicacion, String genero, int prestable) {
    
        this.titulo = titulo;
        this.autor = autor;
        this.añoPublicacion = añoPublicacion;
        this.genero = genero;
        this.prestable = prestable;
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

    public int getPrestable(int prestable) {
        return prestable;
    }

    public void setPrestable(int prestable) {
        this.prestable = prestable;
    }

    @Override
    public String toString() {
        return "libro{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", a\u00f1oPublicacion=" + añoPublicacion + ", genero=" + genero + ", prestable=" + prestable + '}';
    }
}
