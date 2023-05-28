package universidadc1g14.AccesoADatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import universidadc1g14.Entidades.Materia;

/**
 *
 * @author RAFAEL
 */
public class MateriaData {
    // Atributo de la clase

    private Connection c = null;
// Cosnstructor

    public MateriaData() {
        c = Conexion.getConexion();
    }
    
    public void guardarMateria(Materia m) {

        String sql = "INSERT INTO materia (nombre,año,estado) VALUES (?,?,?)";
        try {
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getAnio());
            ps.setBoolean(3, m.isEstado());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                m.setIdMateria(rs.getInt("idMateria"));
                JOptionPane.showMessageDialog(null, "Materia añadida con exito.");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia -" + ex.getMessage());
        }

    }
    
    public Materia buscarMateria(int id) {
       Materia m = new Materia();
        String sql = "SELECT* FROM materia WHERE idMateria=? AND estado = 1";
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m.setIdMateria(rs.getInt("idMateria"));
                m.setNombre(rs.getString("nombre"));
                m.setAnio(rs.getInt("año"));
                m.setEstado(rs.getBoolean("estado"));
            } else {
                JOptionPane.showMessageDialog(null, "El Id ingresado no corresponde a ninguna materia activa");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia " + ex.getMessage());
        }
        return m;
    }
    
    public List<Materia> listarMaterias() {
        List<Materia> materias = new ArrayList<>();

        try {
            String sql = "SELECT * FROM materia WHERE  estado = 1";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia m = new Materia();

                m.setIdMateria(rs.getInt("idMateria"));
                m.setNombre(rs.getString("nombre"));
                m.setAnio(rs.getInt("año"));
                m.setEstado(rs.getBoolean("estado"));
                materias.add(m);
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia");
        }
        return materias;
    }

    public void modificarMateria(Materia m) {
        String sql = "UPDATE materia SET nombre=?, año =? WHERE idMateria=?";

        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getAnio());
            ps.setInt(3, m.getIdMateria());
            int exito = ps.executeUpdate();

            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Modificado Exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "La materia no existe");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder ala tabla Materia " + ex.getMessage());

        }
    }

    public void eliminarMateria(int id) {
        try {
            String sql = " UPDATE materia SET estado =0 WHERE idMateria =? ";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            int fila = ps.executeUpdate();

            if (fila == 1) {
                JOptionPane.showMessageDialog(null, "Se Elimino la Materia");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la Tabla Materia");

        }
    }
    
}
