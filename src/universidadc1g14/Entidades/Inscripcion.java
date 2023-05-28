package universidadc1g14.Entidades;

/**
 *
 * @author RAFAEL
 */
public class Inscripcion {
    
    private int idInscripcion;
    private double nota;
    private Alumno idAlumno;
    private Materia idMateria;

    public Inscripcion() {
    }

    public Inscripcion(double nota, Alumno idAlumno, Materia idMateria) {
        this.nota = nota;
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
    }
    
    

    public Inscripcion(int idInscripcion, double nota, Alumno idAlumno, Materia idMateria) {
        this.idInscripcion = idInscripcion;
        this.nota = nota;
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Alumno getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }
    
    @Override
    public String toString() {
        return "Id Incripcion: " + idInscripcion + " - Nota: " + nota + " - " + idAlumno +idMateria + "\n" ;
    }
    
}
