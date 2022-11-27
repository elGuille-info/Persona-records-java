//
// Datos de personas y salida y entrada en un fichero. (27/nov/22 19.12)
//
// Este proyecto utiliza un record para los datos de una persona.
// Lee los datos del fichero (si existe)
// Pide más datos para añadir.
// Guarda los datos en el fichero.
//

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class PersonaMain {
    public static void main(String[] args) throws IOException {
        // El fichero donde se guardan las personas.
        String ficPersonas = "./out/personas.txt";
        // Colección para guardar los datos de las personas.
        ArrayList<Persona> personas = new ArrayList<>();

        if (leerDatos(ficPersonas, personas)) {
            System.out.println("\nError al leer los datos de las personas.");
            System.out.println();
        }
        else {
            System.out.println("\nLas personas del fichero son:");
            mostrarPersonas(personas);
        }

        System.out.println("Indica las personas a añadir a la lista:");
        while (true) {
            Persona p = addPersona();
            if (p == null) {
                break;
            }
            personas.add(p);
        }

        // Mostrar las personas introducidas.
        System.out.println();
        System.out.println("Las personas son:");
        mostrarPersonas(personas);

        System.out.println();
        // Guardar las personas.
        guardarDatos(ficPersonas, personas);
    }

    /**
     * Mostrar los datos de las personas de la colección indicada.
     *
     * @param personas ArrayList con las personas a mostrar.
     */
    private static void mostrarPersonas(ArrayList<Persona> personas) {
        for (var p: personas) {
            //System.out.println(p);
            p.mostrarDatos(true);
            System.out.println();
        }
    }

    /**
     * Leer los datos de las personas si existe el fichero indicado.
     *
     * @param ficPersonas El fichero donde están guardados los datos de las personas.
     * @param personas ArrayList de tipo Persona donde se agregarán los datos leídos.
     * @return True si se ha producido un error al leer los datos, en otro caso false.
     * @throws IOException Capturar el error.
     */
    static boolean leerDatos(String ficPersonas, ArrayList<Persona> personas) throws IOException {
        boolean hayError = false;
        String nombre, apellidos;
        LocalDate fechaNacimiento;

        System.out.printf("Leyendo los datos de %s.\n", ficPersonas);
        File fic = new File(ficPersonas);
        if (!fic.exists()) {
            System.out.println("No existe el fichero con los datos.");
            return true;
        }
        BufferedReader sr;
        try {
            sr = new BufferedReader(new FileReader(ficPersonas));
        }
        catch (Exception e) {
            System.out.printf("Error al acceder al fichero de datos:\n%s.\n", e.getMessage());
            return true;
        }

        // Mientras haya datos en el fichero.
        while (sr.ready()) {
            // Leer el nombre o el fin del fichero.
            String s = sr.readLine();
            nombre = s;
            // Comprobar si hay datos en el fichero.
            if (sr.ready()) {
                // Leer los apellidos.
                apellidos = sr.readLine();
            }
            else {
                System.out.println("\tNo hay más datos, se finaliza la lectura de datos.");
                break;
            }
            if (sr.ready()) {
                // Leer la fecha de nacimiento.
                s = sr.readLine();
                try {
                    fechaNacimiento = LocalDate.parse(s);
                } catch (DateTimeParseException e) {
                    System.out.printf("\tError al leer la fecha '%s'.\n", s);
                    System.out.println("\tNo se siguen leyendo más datos.");
                    System.out.printf("\tEl último nombre que se ha intentado leer es: '%s'\n", nombre);
                    hayError = true;
                    break;
                }
            }
            else {
                System.out.println("\tNo hay más datos, se finaliza la lectura de datos.");
                break;
            }
            personas.add(new Persona(nombre, apellidos, fechaNacimiento));
        }
        sr.close();
        return hayError;
    }

    /**
     * Guarda los datos de las personas en un fichero.
     *
     * @param ficPersonas El fichero donde se guardarán los datos.
     * @param personas ArrayList de tipo Persona con los datos a guardar.
     * @throws IOException Capturar el error.
     */
    static void guardarDatos(String ficPersonas, ArrayList<Persona> personas) throws IOException {
        BufferedWriter sw = new BufferedWriter( new FileWriter(ficPersonas));
        System.out.printf("Guardando los datos en %s.", ficPersonas);
        for (var p: personas) {
            sw.write((p.nombre()));
            sw.newLine();
            sw.write(p.apellidos());
            sw.newLine();
            sw.write(p.fechaNacimiento().toString());
            sw.newLine();
        }

        sw.close();
    }

    /**
     * Pide los datos de una persona para añadirlo a la colección personas del método main.
     *
     * @return Un valor Personal o null si se ha indicado no añadir y salir.
     * @throws IOException Capturar el error.
     */
    static Persona addPersona() throws IOException {
        String res, nombre, apellidos;
        LocalDate fechaNacimiento;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Indica los datos de la persona a añadir (0 en cualquier dato para no añadir y salir).");
        while (true) {
            System.out.print("Nombre: ");
            res = in.readLine();
            if (res.equals("0")) {
                return null;
            }
            else if (res.trim().equals("")) {
                System.out.println("\tDebes indicar un nombre, no se admiten cadenas vacías.");
                continue;
            }
            nombre = res;
            break;
        }
        while (true) {
            System.out.print("Apellidos: ");
            res = in.readLine();
            if (res.equals("0")) {
                return null;
            }
            else if (res.trim().equals("")) {
                System.out.println("\tDebes indicar los apellidos, no se admiten cadenas vacías.");
                continue;
            }
            apellidos = res;
            break;
        }

        while (true) {
            System.out.print("Fecha nacimiento en formato (año-mes-día): ");
            res = in.readLine();
            if (res.equals("0")) {
                return null;
            }
            else if (res.trim().equals("")) {
                System.out.println("\tDebes indicar una fecha, no se admiten cadenas vacías.");
                continue;
            }
            try {
                fechaNacimiento = LocalDate.parse(res);
                break;
            }
            catch (Exception e) {
                System.out.printf("\tLa fecha '%s' no es válida, debe estar en el formato yyyy-MM-dd.\n", res);
            }
        }
        return new Persona(nombre, apellidos, fechaNacimiento);
    }
}
