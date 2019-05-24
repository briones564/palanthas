/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import logicaNegocio.LogicaLectores;
import logicaNegocio.LogicaLibros;
/**
 *
 * @author dam08
 */
public class Interfaz {
     
    static LogicaLectores logicaLectores = new LogicaLectores();
    static LogicaLibros logicaLibros = new LogicaLibros();
    
     public static void main(String[] args) { 
     
        logicaLectores.reset();
        /*System.out.print(logicaLectores.visualizarLectores());
        if(logicaLectores.bajaLector(4)) System.out.println("BAJA");
        if(logicaLectores.altaLector("Hodor","Hodor","Hodor","Hodor","Hodor")) System.out.println("ALTA");
        if(logicaLectores.altaLector("Hodor2","Hodor2","Hodor","Hodor","Hodor")) System.out.println("ALTA");
        if(logicaLectores.altaLector("Hodor3","Hodor3","Hodor","Hodor","Hodor")) System.out.println("ALTA");
        if(logicaLectores.modificarLector("Pau", "Karl", "Karl","Karl","Karl","Karl")) System.out.println("MODIFICADO");
        System.out.print(logicaLectores.visualizarLectores());
        System.out.println("NIF encontrado: " + logicaLectores.buscarNIF("Hodor").toString());
        System.out.println("ID encontrado: " + logicaLectores.buscarID(7).toString());*/
        
        logicaLibros.reset();
        /*System.out.print(logicaLibros.visualizarLibros());
        if(logicaLibros.altaLibro("Hodor","Hodor","Hodor","Hodorrrrrrrrrr",true)) System.out.println("ALTA");
        if(logicaLibros.altaLibro("Hodor2","Hodor2","Hodor2","Hodor2",true)) System.out.println("ALTA");
        if(logicaLibros.altaLibro("Hodor3","Hodor3","Hodor3","Hodor3",true)) System.out.println("ALTA");
        if(logicaLibros.modificarLibro("Rojo y negro", "Karl", "Karl","Karl","Karl",true)) System.out.println("MODIFICADO");
        System.out.print(logicaLibros.visualizarLibros());
        System.out.println("Titulo encontrado: " + logicaLibros.buscarTitulo("El niño 44").toString());      
        System.out.println("ID encontrado: " + logicaLibros.buscarID(14).toString());*/
        
        logicaLibros.prestar(4, 2);
        logicaLibros.prestar(5, 6);
        logicaLibros.prestar(6, 4);
        System.out.print(logicaLibros.verPrestamos());
        logicaLibros.devolver(1);
        System.out.print(logicaLibros.visualizarLibros());
        System.out.print(logicaLibros.verPrestamos());
        System.out.println(logicaLibros.verLectoresAlquiler());
    }
}
