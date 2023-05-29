package universidadc1g14;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import universidadc1g14.Entidades.Alumno;
import universidadc1g14.AccesoADatos.AlumnoData;
import universidadc1g14.AccesoADatos.InscripcionData;
import universidadc1g14.AccesoADatos.MateriaData;
import universidadc1g14.Entidades.Inscripcion;
import universidadc1g14.Entidades.Materia;

/**
 *
 * @author RAFAEL
 */
public class UniversidadC1G14 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int op, op1, op2, op3;
        AlumnoData ad = new AlumnoData();
        MateriaData md = new MateriaData();
        InscripcionData iData = new InscripcionData();

        do {
            op = Integer.parseInt(JOptionPane.showInputDialog("Menu Principal\n"
                    + "1- Opciones Alumno\n"
                    + "2- Opciones Materia\n"
                    + "3- Opciones Inscripcion\n"
                    + "4- Salir"));
            switch (op) {

                case 1:
                    try {
                    do {
                        //int op1;
                        op1 = Integer.parseInt(JOptionPane.showInputDialog("Menu Alumnos\n"
                                + "1- Ingresar Alumno\n"
                                + "2- Buscar Alumno por Id\n"
                                + "3- Buscar Alumno por DNI\n"
                                + "4- Listar Alumnos\n"
                                + "5- Modificar Alumno\n"
                                + "6- Eliminar Alumno\n"
                                + "7- Salir a menu principal"));
                        switch (op1) {
                            case 1:
                       try {
                                int dni;
                                dni = Integer.parseInt(JOptionPane.showInputDialog("Ingresa DNI del alumno"));
                                String apellido;
                                apellido = JOptionPane.showInputDialog("Ingresa Apellido del alumno");
                                String nombre;
                                nombre = JOptionPane.showInputDialog("Ingresa Nombre del alumno");
                                String fNacimiento;
                                fNacimiento = JOptionPane.showInputDialog("Ingresa Fecha de nacimiento del alumno");

                                boolean estado = true;
                                Alumno a = new Alumno(dni, apellido, nombre, LocalDate.parse(fNacimiento), estado);
                                ad.guardarAlumno(a);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Error en el ingreso de datos");
                            }
                            break;
                            case 2:
                                int id2;
                                id2 = Integer.parseInt(JOptionPane.showInputDialog("Ingresa ID del alumno"));
                                JOptionPane.showMessageDialog(null, ad.buscarAlumno(id2));
                                break;
                            case 3:
                                int dni;
                                dni = Integer.parseInt(JOptionPane.showInputDialog("Ingresa DNI del alumno"));
                                JOptionPane.showMessageDialog(null, ad.buscarAlumnoPorDni(dni));
                                break;
                            case 4:
                                JOptionPane.showMessageDialog(null, ad.listarAlumnos());
                                break;
                            case 5:
                                int d;
                                d = Integer.parseInt(JOptionPane.showInputDialog("Ingresa Id del alumno"));
                                Alumno al = ad.buscarAlumno(d);
                                int dn;
                                dn = Integer.parseInt(JOptionPane.showInputDialog("Ingresa DNI del alumno"));
                                String apellido;
                                apellido = JOptionPane.showInputDialog("Ingresa Apellido del alumno");
                                String nombre;
                                nombre = JOptionPane.showInputDialog("Ingresa Nombre del alumno");
                                String fNacimiento;
                                fNacimiento = JOptionPane.showInputDialog("Ingresa Fecha de nacimiento del alumno");
                                al.setDni(dn);
                                al.setApellido(apellido);
                                al.setNombre(nombre);
                                al.setFechaNacimiento(LocalDate.parse(fNacimiento));
                                ad.modificarAlumno(al);
                                break;
                            case 6:
                                int id1;
                                id1 = Integer.parseInt(JOptionPane.showInputDialog("Ingresa Id del alumno para eliminar"));
                                ad.eliminarAlumno(id1);
                                break;
                        }
                    } while (op1 != 7);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Solo se admiten numeros");
                }
                break;
                case 2:
                    try {
                    do {
                        op2 = Integer.parseInt(JOptionPane.showInputDialog("Menu Materia\n"
                                + "1- Ingresar Materia\n"
                                + "2- Buscar Materia por Id\n"
                                + "3- Listar Materias\n"
                                + "4- Modificar Materia\n"
                                + "5- Eliminar Materia\n"
                                + "6- Salir a menu principal"));

                        switch (op2) {
                            case 1:
                                try {
                                String nombre;
                                nombre = JOptionPane.showInputDialog("Ingresa Nombre de la materia");
                                int anio;
                                anio = Integer.parseInt(JOptionPane.showInputDialog("Ingresa año al que pertenece la materia"));
                                boolean activo = true;
                                Materia m = new Materia(nombre, anio, activo);
                                md.guardarMateria(m);
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Solo se admiten numeros");
                            }
                            break;
                            case 2:
                                int id2;
                                id2 = Integer.parseInt(JOptionPane.showInputDialog("Ingresa ID de la materia"));
                                JOptionPane.showMessageDialog(null, md.buscarMateria(id2));
                                break;
                            case 3:
                                JOptionPane.showMessageDialog(null, md.listarMaterias());
                                break;
                            case 4:
                                int idM;
                                idM = Integer.parseInt(JOptionPane.showInputDialog("Ingresa ID de la materia"));
                                Materia m1 = md.buscarMateria(idM);
                                String nombre;
                                nombre = JOptionPane.showInputDialog("Ingresa Nombre de la materia");
                                int anio;
                                anio = Integer.parseInt(JOptionPane.showInputDialog("Ingresa año al que pertenece la materia"));
                                m1.setNombre(nombre);
                                m1.setAnio(anio);
                                md.modificarMateria(m1);
                                break;
                            case 5:
                                int idMa;
                                idMa = Integer.parseInt(JOptionPane.showInputDialog("Ingresa ID de la materia"));
                                md.eliminarMateria(idMa);
                        }
                    } while (op2 != 6);

                } catch (NumberFormatException e) {

                }
                break;
                case 3:
                    try {
                    do {
                        op3 = Integer.parseInt(JOptionPane.showInputDialog("Menu Inscripcion\n"
                                + "1- Ingresar Inscripcion\n"
                                + "2- Borrar inscripcion\n"
                                + "3- Actualizar nota\n"
                                + "4- Listar Inscripciones\n"
                                + "5- Listar Inscripciones por alumno\n"
                                + "6- Salir"));

                        switch (op3) {
                            case 1:
                    try {
                                double nota;
                                nota = Double.parseDouble(JOptionPane.showInputDialog("Ingresa nota de la materia"));
                                int i;
                                i = Integer.parseInt(JOptionPane.showInputDialog("Ingresa Id alumno"));
                                Alumno idA = ad.buscarAlumno(i);
                                int im;
                                im = Integer.parseInt(JOptionPane.showInputDialog("Ingresa Id materia"));
                                Materia idM = md.buscarMateria(im);
                                Inscripcion ins = new Inscripcion(nota, idA, idM);
                                iData.guardarInscripcion(ins);
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Solo se admiten numeros");
                            }
                            break;
                            case 2:
                                int idA;
                                idA = Integer.parseInt(JOptionPane.showInputDialog("Ingresa Id alumno"));
                                int im;
                                im = Integer.parseInt(JOptionPane.showInputDialog("Ingresa Id materia"));
                                iData.eliminarInscripcionMateria(idA, im);
                                break;
                            case 3:
                                int i;
                                i = Integer.parseInt(JOptionPane.showInputDialog("Ingresa Id alumno"));
                                int iMa;
                                iMa = Integer.parseInt(JOptionPane.showInputDialog("Ingresa Id materia"));
                                double nota;
                                nota = Double.parseDouble(JOptionPane.showInputDialog("Ingresa nota de la materia"));
                                iData.actualizarNota(i, iMa, nota);
                                break;
                            case 4:
                                JOptionPane.showMessageDialog(null, iData.listarInscripciones());
                                break;
                            case 5:
                                int iA;
                                iA = Integer.parseInt(JOptionPane.showInputDialog("Ingresa Id alumno"));
                                JOptionPane.showMessageDialog(null, iData.listarInscripcionesPorAlumno(iA));
                                break;
                        }
                    } while (op3 != 6);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Solo se admiten numeros");
                }
                break;
            }

        } while (op != 4);

    }

}
