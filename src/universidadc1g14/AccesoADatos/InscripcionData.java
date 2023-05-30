package universidadc1g14.AccesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import universidadc1g14.Entidades.Alumno;
import universidadc1g14.Entidades.Inscripcion;
import universidadc1g14.Entidades.Materia;

/**
 *
 * @author RAFAEL
 */
    
public class InscripcionData {
    
     private Connection c = null;
// Cosnstructor

    public InscripcionData() {
        c = Conexion.getConexion();
    }
    
    private Alumno rAlumno(int id) {
        AlumnoData ad = new AlumnoData();
        return ad.buscarAlumno(id);
    }

    private Materia rMateria(int id) {
        MateriaData md = new MateriaData();
        return md.buscarMateria(id);
    }
    
    public void guardarInscripcion(Inscripcion i) {

        String sql = "INSERT INTO inscripcion (nota, idAlumno, idMateria) VALUES (?,?,?)";

        try {
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, i.getNota());
            ps.setInt(2, i.getIdAlumno().getIdAlumno());
            ps.setInt(3, i.getIdMateria().getIdMateria());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                i.setIdInscripcion(rs.getInt("idInscripcion"));
                JOptionPane.showMessageDialog(null, "Inscripcion agregada");
            } else {

                JOptionPane.showMessageDialog(null, "No se añadio la inscripcion");

            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion" + ex.getMessage());
        }
    }
       
    
    public void actualizarNota(int idA, int idM, double not) {
        String sql = "UPDATE inscripcion SET nota=? WHERE idAlumno=? AND idMateria=?";

        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sql);
            ps.setDouble(1, not);
            ps.setInt(2, idA);
            ps.setInt(3, idM);
            
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Nota actualizada Exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "La inscripcion no existe");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder ala tabla Inscripcion" + ex.getMessage());

        }
    }

    public void eliminarInscripcionMateria(int idA, int idM) {
        try {
            String sql = " DELETE FROM inscripcion WHERE idAlumno =? AND idMateria =? ";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, idA);
            ps.setInt(2, idM);
            int fila = ps.executeUpdate();

            if (fila == 1) {
                JOptionPane.showMessageDialog(null, "Se Elimino la inscripcion");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la Tabla Inscripcion");

        }
    }
    
    public List<Inscripcion> listarInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();

        try {
            String sql = "SELECT * FROM inscripcion WHERE  nota >= 0";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Alumno a;
            Materia m;
            while (rs.next()) {
                Inscripcion ins = new Inscripcion();
                a = rAlumno(rs.getInt("idAlumno"));
                m = rMateria(rs.getInt("idMateria"));
                ins.setIdInscripcion(rs.getInt("idInscripcion"));
                ins.setNota(rs.getDouble("nota"));
                ins.setIdAlumno(a);
                ins.setIdMateria(m);
                inscripciones.add(ins);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
        return inscripciones;
    }

    public List<Inscripcion> listarInscripcionesPorAlumno(int idA) {
        List<Inscripcion> inscripciones = new ArrayList<>();

        try {
            String sql = "SELECT * FROM inscripcion WHERE idAlumno=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, idA);
            ResultSet rs = ps.executeQuery();
            Alumno a;
            Materia m;
            while (rs.next()) {
                Inscripcion inscrip = new Inscripcion();
                a = rAlumno(idA);
                m = rMateria(rs.getInt("idMateria"));
                inscrip.setIdInscripcion(rs.getInt("idInscripcion"));
                inscrip.setNota(rs.getDouble("nota"));
                inscrip.setIdAlumno(a);
                inscrip.setIdMateria(m);
                inscripciones.add(inscrip);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
        return inscripciones;
    }
    
    public List<Materia> listarmateriasCursadas(int idM) {
        List<Materia> materias = new ArrayList<>();

        try {
            String sql = "SELECT materia.*"
                    + "FROM materia JOIN inscripcion ON (materia.idMateria = inscripcion.idMateria)"
                    + "WHERE idAlumno = ?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, idM);
            ResultSet rs = ps.executeQuery();
            Materia m;
            while (rs.next()) {
                m = new Materia();
                m.setIdMateria(rs.getInt("idMateria"));
                m.setNombre(rs.getString("Nombre"));
                m.setAnio(rs.getInt("año"));
                materias.add(m);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion" + ex.getMessage());
        }
        return materias;
    }
    
    public List<Materia> listarmateriasNoCursadas(int idM) {
        List<Materia> materias = new ArrayList<>();

        try {
            String sql = "SELECT * FROM materia WHERE idMateria NOT IN (SELECT materia.idMateria FROM materia JOIN inscripcion ON (materia.idMateria = inscripcion.idMateria) WHERE idAlumno = ?)";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, idM);
            ResultSet rs = ps.executeQuery();
            Materia m;
            while (rs.next()) {
                m = new Materia();
                m.setIdMateria(rs.getInt("idMateria"));
                m.setNombre(rs.getString("Nombre"));
                m.setAnio(rs.getInt("año"));
                materias.add(m);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion" + ex.getMessage());
        }
        return materias;
    }
    
    public List<Alumno> listarAlumnoXMateria(int idM){
         List<Alumno> alumnos = new ArrayList<>();

        try {
            String sql = "SELECT alumno.* FROM alumno JOIN inscripcion ON (alumno.idAlumno = inscripcion.idAlumno) WHERE idMateria = ?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, idM);
            ResultSet rs = ps.executeQuery();
            Alumno a;
            while (rs.next()) {
                a = new Alumno();
                a.setIdAlumno(rs.getInt("idAlumno"));
                a.setApellido(rs.getString("apellido"));
                a.setNombre(rs.getString("nombre"));
                alumnos.add(a);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla" + ex.getMessage());
        }
        return alumnos;
    }
    
}
