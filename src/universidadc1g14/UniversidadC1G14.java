package universidadc1g14;

import java.time.LocalDate;
import universidadc1g14.Entidades.Alumno;
import universidadc1g14.AccesoADatos.AlumnoData;

/**
 *
 * @author RAFAEL
 */
public class UniversidadC1G14 {

    
    public static void main(String[] args) {
        // TODO code application logic here
           Alumno alumno = new Alumno(3423456,"Messi","Lionel",LocalDate.parse("1987-06-24"),true);
           AlumnoData ad = new AlumnoData();
           
           
           ad.guardarAlumno(alumno);
           ad.buscarAlumno(1);
          ad.modificarAlumno(alumno);
           ad.eliminarAlumno(2);
           
           
    }

}
