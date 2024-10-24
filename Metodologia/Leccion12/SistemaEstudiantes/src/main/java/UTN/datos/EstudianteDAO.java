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
    public boolean buscarEstudiantePorId(Estudiante estudiante){
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

    public static  void main(String[] args){
        //Listar los estudiantes
        var estudianteDao= new EstudianteDAO();
        System.out.println("Listado de estudiante: ");
        List<Estudiante>estudiantes=estudianteDao.listarEstudiantes();
        estudiantes.forEach(System.out::println);//Funcion lamba para imprimir

        //Buscar por id
        

    }
}
