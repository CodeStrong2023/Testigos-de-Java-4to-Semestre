package UTN.datos;

import UTN.dominio.Estudiante;
import static UTN.conexxion.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    //Metod listar
    public List<Estudiante>listarEstudiantes(){
        List<Estudiante> estudiantes=new ArrayList<>();
        //Creamos algunos objetos que son necesario para comunicarnos con la base de datos
        PreparedStatement ps; //Envia la sentencia a la base de datos
        ResultSet rs; //Obtenemos el resultado de la base de datos
        //Creamos un objeto de tipo conexion
        Connection con = getConnection();
        String sql= "SELECT * FROM estudiantes2024 ORDER BY idestudiantes2024";
        try{
            ps=con.prepareStatement(sql);
            rs =ps.executeQuery();
            while (rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("idestudiantes2024"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                //Falta agregarlo a la lista
                estudiantes.add(estudiante);
            }

        }catch (Exception e){
            System.out.println("Ocurrio un error al seleccionar datos: "+e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar la conexions:"+e.getMessage());
            }
        }//Fin finally
        return estudiantes;
    } //fin metodo listar

    //Metodo por id -> fin by id
    public boolean buscarEstudiantePorId (Estudiante estudiante){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConnection();
        String sql = "SELECT * FROM estudiantes2024 WHERE idestudiantes2024=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,estudiante.getIdEstudiante());
            rs= ps.executeQuery();
            if(rs.next()){
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                return true; //Se encontro un registro
            }//Fin if
        } catch (Exception e){
            System.out.println("Ocurrio un error al buscar estudiante: "+e.getMessage());
        }//Fin del catch
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar la conexion: "+e.getMessage());
            }//fin catch
        }//Fin FINALLY
        return false;
    }

    //Metodo agregar un nuevo estudiante

    public boolean agregarEstudiante (Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "INSERT INTO estudiantes2024 (nombre, apellido, telefono, email) VALUES (?, ?, ? ,?)";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,estudiante.getNombre());
            ps.setString(2,estudiante.getApellido());
            ps.setString(3,estudiante.getTelefono());
            ps.setString(4,estudiante.getEmail());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Ocurrio un error al agregar el estudiante: "+e.getMessage());
        }//fin del catch
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: "+e.getMessage());
            }//fin catch
        }//fin finaly
        return false;
    }//fin metodo agregar estudiante

    //Metodo para modificar estudiante

    public boolean modificarEstudiante (Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConnection();
        String sql="UPDATE estudiantes2024 SET nombre=?, apellido=?, telefono=?, email=? WHERE idestudiantes2024=?";
        try {
            ps= con.prepareStatement(sql);
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getApellido());
            ps.setString(3, estudiante.getTelefono());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getIdEstudiante());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al modificar estudiante"+e.getMessage());
        }//Fin catch
        finally {
            try {
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion");
            }//Fin catch
        }//Fin finally
        return  false;
    }//Fin metodo modificarEstudiante

    public boolean eliminarEstudiante (Estudiante estudiante){
        PreparedStatement ps;
        Connection con = getConnection();
        String sql = "DELETE FROM estudiantes2024 WHERE idestudiantes2024=?";
        try{
            ps= con.prepareStatement(sql);
            ps.setInt(1,estudiante.getIdEstudiante());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error al eliminar estudiante: "+e.getMessage());
        }
        finally{
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar la conexion: "+e.getMessage());
            }
        }
        return false;
    }


    public static  void main(String[] args){

        var estudianteDao= new EstudianteDAO();
        //Modificar estudiante

        //var estudianteModificado= new Estudiante(1,"Juan Carlos","Juarez","555665666","Juan@mail.com");
        //var modificado = estudianteDao.modificarEstudiante(estudianteModificado);
        //if(modificado)
        //    System.out.println("Estudiante modificado: "+estudianteModificado);
        //else
        //   System.out.println("No se modifico el estudiante: "+estudianteModificado);

        //Agregar estudiante
        //var nuevoEstudiante= new Estudiante("Carlos","Lara","621651986","carlosl@mail.com");
        //var agregado= estudianteDao.agregarEstudiante(nuevoEstudiante);
         //if(agregado)
        //     System.out.println("Estudiante agregado: "+nuevoEstudiante);
        // else
         //   System.out.println("No se ha agregado estudiante: "+nuevoEstudiante);

        //Eliminar estudiante con id 3
        var estudianteEliminar=new Estudiante(5);
        var eliminado=estudianteDao.eliminarEstudiante(estudianteEliminar);
        if(eliminado)
            System.out.println("Estudiante eliminado: "+estudianteEliminar);
        else
            System.out.println("No se elimino estudiante: "+estudianteEliminar);


        //Listar los estudiantes
        System.out.println("Listado de estudiante: ");
        List<Estudiante>estudiantes=estudianteDao.listarEstudiantes();
        estudiantes.forEach(System.out::println);//Funcion lamba para imprimir


        //Buscar por id
        //var estudiante1= new Estudiante(1);
        //System.out.println("Estudiantes antes de la busqueda: "+estudiante1);
        //var encontrado= estudianteDao.buscarEstudiantePorId(estudiante1);
        //if(encontrado)
        //    System.out.println("Estudiante encontrado: "+estudiante1);
        //else
        //    System.out.println("No se encontro el estudiante: "+estudiante1.getIdEstudiante());

    }
}
