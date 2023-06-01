package universidadc1g14.Entidades;

import java.time.LocalDate;

/**
 *
 * @author RAFAEL
 */
public class Alumno {
    private static int idActual = 1;
    private int idAlumno;
    private int dni;
    private String apellido;
    private String nombre;
    private LocalDate fechaNacimiento;
    private boolean estado;

// Todas las clases deben tener los tres constructores (vacio, todos, y todos meneos idAlumno
    public Alumno() {
    }

    public Alumno(int dni, String apellido, String nombre, LocalDate fechaNacimiento, boolean estado) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }
    
    public Alumno(int idAlumno, int dni, String apellido, String nombre, LocalDate fechaNacimiento, boolean estado) {
        this.idAlumno = idAlumno;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
    }

    public static int getIdActual() {
        return idActual;
    }

    public static void setIdActual(int idActual) {
        Alumno.idActual = idActual;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "IdAlumno: " + idAlumno + " - Apellido: " + apellido + " - Nombre: " + nombre + "\n";
    }

}
