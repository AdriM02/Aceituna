import DAO.*;
import modelos.*;

import java.sql.SQLException;
import java.time.format.DateTimeParseException;
import java.util.*;


public class Main {
    static String menuPrincipal = "________________MENU_______________" +
            "\nSelecciona una Opción: " +
            "\n1. Mostrar los trabajadores de una determinada cuadrilla.\n" +
            "2. Mostrar las cuadrillas que supervisa un determinado trabajador.\n" +
            "3. Mostrar los olivares donde trabaja una determinada cuadrilla.\n" +
            "4. Mostrar las cuadrillas que trabajan en un determinado olivar.\n" +
            "5. Mostrar las almazaras donde lleva aceituna una determinada cuadrilla.\n" +
            "6. Mostrar la producción en una fecha concreta, de una cuadrilla\n" +
            "concreta en una almazara concreta.\n" +
            "7. Mostrar la producción hasta una determinada fecha, de una\n" +
            "determinada almazara.\n" +
            "8. Mostrar la producción hasta una determinada fecha, de un\n" +
            "determinado olivar\n" +
            "9. Mostrar la producción hasta una determinada fecha, de una\n" +
            "cuadrilla determinada \n" +
            "10.Salir";

    public static void main(String[] args) throws SQLException {
        String fecha = "";
        CuadrillaDAO cuadrillaDAO = new CuadrillaDAO();
        int idCuadrilla =0;
        List<Cuadrilla> cuadrillas;
        AlmazaraDAO almazaraDAO = new AlmazaraDAO();
        int idAlmazara = 0;
        List<Almazara> almazaras;
        OlivarDAO olivarDAO = new OlivarDAO();
        int idOlivar = 0;
        List<Olivar> olivares;

        ProduccionDAO produccionDAO = new ProduccionDAO();
        Produccion produccion;
        List<Produccion> producciones;

        TrabajadorDAO trabajadorDAO = new TrabajadorDAO();
        int idTrabajador;
        List<Trabajador> trabajadores;
        Scanner sc = new Scanner(System.in);
        System.out.println(menuPrincipal);
        String opcion = sc.nextLine();

        while (!Objects.equals(opcion, "10")) {
            switch (opcion) {
                case "1": {

                    System.out.println("Introduce el id de la cuadrilla:");
                    try {
                        idCuadrilla = sc.nextInt();
                       trabajadores = cuadrillaDAO.getTrabajadoresPorCuadrilla(idCuadrilla);
                       for (Trabajador trabajador : trabajadores){
                           System.out.println(trabajador);
                       }

                    }catch (InputMismatchException e){
                        System.out.println("No has introducido un numero");
                    }



                    sc.nextLine();
                    System.out.println("\n"+menuPrincipal);
                    opcion = sc.nextLine();
                    break;
                }
                case "2": {
                    System.out.println("Introduce el id del trabajador:");
                    try {
                        idTrabajador = sc.nextInt();
                        cuadrillas = trabajadorDAO.geCuadrillasPorSupervisor(idTrabajador);
                        for (Cuadrilla cuadrilla : cuadrillas){
                            System.out.println(cuadrilla);
                        }

                    }catch (InputMismatchException e){
                        System.out.println("No has introducido un numero");
                    }

                    sc.nextLine();
                    System.out.println("\n"+menuPrincipal);
                    opcion = sc.nextLine();
                    break;
                }
                case "3":{
                    System.out.println("Introduce el id de la cuadrilla:");
                    try {
                        idCuadrilla = sc.nextInt();
                        olivares = cuadrillaDAO.getOlivaresPorCuadrilla(idCuadrilla);
                        for (Olivar olivar : olivares){
                            System.out.println(olivar);
                        }

                    }catch (InputMismatchException e){
                        System.out.println("No has introducido un numero");
                    }

                    sc.nextLine();
                    System.out.println("\n"+menuPrincipal);
                    opcion = sc.nextLine();
                    break;
                }
                case "4":{
                    System.out.println("Introduce el id del olivar:");
                    try {
                        idOlivar = sc.nextInt();
                        cuadrillas = olivarDAO.geCuadrillasPorOlivar(idOlivar);
                        for (Cuadrilla cuadrilla : cuadrillas){
                            System.out.println(cuadrilla);
                        }

                    }catch (InputMismatchException e){
                        System.out.println("No has introducido un numero");
                    }

                    sc.nextLine();
                    System.out.println("\n"+menuPrincipal);
                    opcion = sc.nextLine();
                    break;

                }
                case "5":{
                    System.out.println("Introduce el id de la cuadrilla:");
                    try {
                        idCuadrilla = sc.nextInt();
                        almazaras = cuadrillaDAO.getAlmazarasPorCuadrilla(idCuadrilla);
                        for (Almazara almazara : almazaras){
                            System.out.println(almazara);
                        }

                    }catch (InputMismatchException e){
                        System.out.println("No has introducido un numero");
                    }

                    sc.nextLine();
                    System.out.println("\n"+menuPrincipal);
                    opcion = sc.nextLine();
                    break;
                }
                case "6":{
                    System.out.println("Introduce la fecha ('yyyy-MM-dd')");
                    fecha = sc.nextLine();
                    try {
                        System.out.println("Introduce el id de la cuadrilla");
                        idCuadrilla = sc.nextInt();
                        System.out.println("Introduce el id de la almazara");
                        idAlmazara = sc.nextInt();
                    }catch (InputMismatchException e){
                        System.out.println("Algún dato introducido es incorrecto");
                    }
                    try{
                        produccion = produccionDAO.getProduccion(fecha,idCuadrilla,idAlmazara);
                        System.out.println(produccion);
                    }catch (DateTimeParseException e){
                        System.out.println("Fecha no válida");
                    }

                    sc.nextLine();
                    System.out.println("\n"+menuPrincipal);
                    opcion = sc.nextLine();
                    break;
                }
                case "7":{
                    try {
                        System.out.println("Introduce la fecha ('yyyy-MM-dd')");
                        fecha = sc.nextLine();
                        System.out.println("Introduce el id de la almazara");
                        idAlmazara = sc.nextInt();
                    
                    }catch (InputMismatchException e){
                        System.out.println("Algún dato introducido es incorrecto");
                    }
                    try{
                        producciones = produccionDAO.getProduccionesEnAlmazara(fecha,idAlmazara);
                        for (Produccion p : producciones){
                            System.out.println(p);
                        }
                    }catch (DateTimeParseException e){
                        System.out.println("Fecha no válida");
                    }

                    sc.nextLine();
                    System.out.println("\n"+menuPrincipal);
                    opcion = sc.nextLine();
                    break;
                }
                case "8":{
                    try {
                        System.out.println("Introduce la fecha ('yyyy-MM-dd')");
                        fecha = sc.nextLine();
                        System.out.println("Introduce el id del olivar");
                        idOlivar = sc.nextInt();
                     
                    }catch (InputMismatchException e){
                        System.out.println("Algún dato introducido es incorrecto");
                    }

                    try{
                        producciones = produccionDAO.getProduccionesEnOlivar(fecha,idOlivar);
                        for (Produccion p : producciones){
                            System.out.println(p);
                        }
                    }catch (DateTimeParseException e){
                        System.out.println("Fecha no válida");
                    }

                    sc.nextLine();
                    System.out.println("\n"+menuPrincipal);
                    opcion = sc.nextLine();
                    break;
                }
                case "9":{
                    try {
                        System.out.println("Introduce la fecha ('yyyy-MM-dd')");
                        fecha = sc.nextLine();
                        System.out.println("Introduce el id de la cuadrilla");
                        idCuadrilla = sc.nextInt();
                    
                    }catch (InputMismatchException e){
                        System.out.println("Algún dato introducido es incorrecto");
                    }

                    try{
                        producciones = produccionDAO.getProduccionesPorCuadrilla(fecha,idCuadrilla);
                        for (Produccion p : producciones){
                            System.out.println(p);
                        }
                    }catch (DateTimeParseException e){
                        System.out.println("Fecha no válida");
                    }

                    sc.nextLine();
                    System.out.println("\n"+menuPrincipal);
                    opcion = sc.nextLine();
                    break;
                }

                default:{
                    System.out.println("\nOpcion no válida");
                    System.out.println(menuPrincipal);
                    opcion = sc.nextLine();
                    break;

                }
            }

        }
        System.out.println("Saliendo...");

    }
}
