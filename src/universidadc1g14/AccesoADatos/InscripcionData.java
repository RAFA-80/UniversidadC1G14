package universidadc1g14.AccesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import universidadc1g14.Entidades.Inscripcion;

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
    
}
