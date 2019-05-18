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
        System.out.print(logicaLectores.visualizarLectores());
        if(logicaLectores.bajaLector(4)) System.out.println("BAJA");
        if(logicaLectores.bajaLector(6)) System.out.println("BAJA");
        if(logicaLectores.altaLector("Hodor","Hodor","Hodor","Hodor","Hodor")) System.out.println("ALTA");
        if(logicaLectores.altaLector("Hodor2","Hodor2","Hodor2","Hodor2","Hodor2")) System.out.println("ALTA");
        if(logicaLectores.altaLector("Hodor3","Hodor3","Hodor3","Hodor3","Hodor3")) System.out.println("ALTA");
        if(logicaLectores.bajaLector(4)) System.out.println("BAJA");
        if(logicaLectores.altaLector("Hodor3","Hodor3","Hodor3","Hodor3","Hodor3")) System.out.println("ALTA");
        if(logicaLectores.modificarLector("Pau", "Karl", "Karl","Karl","Karl","Karl")) System.out.println("MODIFICADO");
        if(logicaLectores.bajaLector(4)) System.out.println("BAJA");
        if(logicaLectores.bajaLector(5)) System.out.println("BAJA");
        if(logicaLectores.bajaLector(6)) System.out.println("BAJA");
        if(logicaLectores.bajaLector(6)) System.out.println("BAJA");
        if(logicaLectores.bajaLector(7)) System.out.println("BAJA");
        if(logicaLectores.bajaLector(8)) System.out.println("BAJA");
        System.out.print(logicaLectores.visualizarLectores());
        System.out.println("NIF encontrado: " + logicaLectores.buscarNIF("99999999R").toString());
        System.out.println("ID encontrado: " + logicaLectores.buscarID(7).toString());
        
        /*logicaLibros.reset();
        System.out.print(logicaLibros.visualizarLibros());
        if(logicaLibros.altaLibro("Hodor","Hodor","Hodor","Hodor","Hodor")) System.out.println("ALTA");
        System.out.print(logicaLibros.visualizarLibros());*/
    }
}
