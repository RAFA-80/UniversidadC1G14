package universidadc1g14.Data;

import java.sql.*;
import javax.swing.JOptionPane;
import universidadc1g14.Clases.Alumno;

/**
 *
 * @author RAFAEL
 */
public class AlumnoData {
// Atributo de la clase
    private Connection c = null;
// Cosnstructor
    public AlumnoData() {
        c = Conexion.getConexion();
    }

    //Realizar metodos para usar can la base de datos(Insertar, actualizar, consultar, borrar)
    
    public void guardarAlumno(Alumno a) {

        String sql = "INSERT INTO alumno (nombre,apellido,dni,fechaNacimiento,estado) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellido());
            ps.setInt(3, a.getDni());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.setBoolean(5, a.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                a.setIdAlumno(rs.getInt("idAlumno"));
                JOptionPane.showMessageDialog(null, "Alumno añadido con exito.");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno -" + ex.getMessage());
        }

    }
    
    
    
}
