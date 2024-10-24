package UTN.presentacion;

import UTN.conexxion.Conexion;
import UTN.datos.EstudianteDAO;
import UTN.dominio.Estudiante;

import java.util.Scanner;

public class SistemaEstudiantesApp {
    public static void main(String[] args) {
        var salir = false;//Recuerden esto ya lo hicimos antes
        var consola = new Scanner(System.in);//Para leer la informacion de la consola
        //Se crea una instancia de la clase servicio,esto lo hacemos fuera del ciclo
        var estudienteDao = new EstudianteDAO();// Esta instancia debe hacerse una vez
        while(!salir){
            try{
                mostrarMenu();//Mostramos el meni
                //Este sera el metodo ejecurarOpciones que devolvera un booleano
                salir= ejecutarOpciones(consola, estudienteDao);//Este arroja una exception
            }catch (Exception e){
                System.out.println("Ocurrio un error al ejecutar la operación"+e.getMessage());
            }
        }//fin while
    }//fin main

    private static void mostrarMenu(){
        System.out.print("""
                *******Sistema de Estudiantes*******
                1. Listar estudiantes
                2. Buscar estudiante
                3. Agregar estudiante
                4. Modificar estudiante
                5. Eliminar estudiante
                6. Salir
                Elige una opción:
                """);
    }
//Metodo para ejecutar las opciones,va a regresar un valor booleano,ya que es el que
//puede modificar el valor de la variable salir,si es verdadero termina el ciclo while
    private static boolean ejecutarOpciones(Scanner consola, EstudianteDAO estudianteDao){
        var opcion= Integer.parseInt(consola.nextLine());
        var salir=false;
        switch (opcion){
            case 1 -> {//Listar estudiantes
                System.out.println("Listado de Estudiantes...");
                //no muestra la informacion,solo recupera la info y regresa una lista
                var estudiantes= estudianteDao.listarEstudiantes();//recibe el lsitado
                //camos a iterar cada objeto de tipo estudiante
                estudiantes.forEach(System.out::println);//para imprimir la lista
            }//fin caso 1
            case 2 -> {//Buscar estudiante por id
                System.out.println("Itroduce el id_estudiante a buscar: ");
                var idEstudiante=Integer.parseInt(consola.nextLine());
                var estudiante= new Estudiante(idEstudiante);
                var encontrado= estudianteDao.buscarEstudiantePorId(estudiante);
                if(encontrado)
                    System.out.println("Estudiante encontrado: "+estudiante);
                else
                    System.out.println("Estudiante NO encontrado: "+estudiante);
            }//fin caso 2
            case 3 ->{//Agregar estudiante
                System.out.println("Agregar estudiante: ");
                System.out.println("Nombre: ");
                var nombre= consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Telefono: ");
                var telefono= consola.nextLine();
                System.out.println("Email: ");
                var email=consola.nextLine();
                //crear objeto estudiante(sin id)
                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado=estudianteDao.agregarEstudiante(estudiante);
                if(agregado)
                    System.out.println("Estudiante agregado: "+estudiante);
                else
                    System.out.println("Estudiante NO agregado: "+estudiante);
            }//fin caso 3
            case 4 -> {//Modificar estudiante
                System.out.println("Modificar estudiante: ");
                //Aqui lo primero es especificar cual es el id del objeto a modificar
                System.out.println("Id Estudiante: ");
                var idEstudiante= Integer.parseInt(consola.nextLine());
                System.out.println("Nombre: ");
                var nombre= consola.nextLine();
                System.out.println("Apellido: ");
                var apellido=consola.nextLine();
                System.out.println("Telefono: ");
                var telefono=consola.nextLine();
                System.out.println("Email: ");
                var email=consola.nextLine();
                //crea el objeto estudiante a modificar
                var estudiante =
                        new Estudiante(idEstudiante,nombre,apellido,telefono,email);
                var modificado= estudianteDao.modificarEstudiante(estudiante);
                if(modificado)
                    System.out.println("Estudiante modificado: "+estudiante);
                else
                    System.out.println("Estudiante NO modificado: "+estudiante);
            }//Fin caso 4
            case 5 ->{//Eliminar estudiante
                System.out.println("Eliminar estudiante: ");
                System.out.println("Id estudiante: ");
                var idEstudiante=Integer.parseInt(consola.nextLine());
                var estudiante= new Estudiante(idEstudiante);
                var eliminado=estudianteDao.eliminarEstudiante(estudiante);
                if(eliminado)
                    System.out.println("Estudiante eliminado: "+estudiante);
                else
                    System.out.println("Estudiante NO eliminado: "+estudiante);
            }//finca caso 5
            case 6 ->{//salir
                System.out.println("Hasta pronto!!!");
                salir=true;
            }//fin caso 6
            default -> System.out.println("Opcion no reconocida,ingrese otra opcion");
        }//fin switch
        return salir;
    }

}//fin clase