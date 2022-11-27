//
// Crear una clase Persona de tipo record está bien si los campos siempre serán fijos.
// Si cualquiera de las "propiedades" pueden cambiar, debería ser de tipo class.
//
// En este ejemplo, los campos son nombre, apellidos y fecha de nacimiento.
// Realmente, tampoco sería válido para record,
// ya que una persona puede cambiar legalmente su nombre y apellidos, aunque no la fecha de nacimiento.
//
// Pero para probar los records y guardarlo en una colección y fichero es válido.
//
import java.time.LocalDate;

public record Persona(String nombre, String apellidos, LocalDate fechaNacimiento) {

    /**
     * Devuelve la edad (en este año) de esta persona.
     *
     * @return Un entero con la diferencia del año actual menos el año de nacimiento.
     */
    public int edad() {
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }

    /**
     * La edad actual.
     *
     * @return La edad actual.
     */
    public int edadActual() {
        var hoy = LocalDate.now();
        // Si el mes de la fecha de nacimiento es mayor del actual, aún no ha cumplido años.
        if (fechaNacimiento.getMonthValue() > hoy.getMonthValue()) {
            return LocalDate.now().getYear() - fechaNacimiento.getYear() -1;
        }
        // Si es el mismo mes, pero el día de nacimiento es posterior, aún no ha cumplido años.
        else if (fechaNacimiento.getMonthValue() == hoy.getMonthValue()) {
            if (fechaNacimiento.getDayOfMonth() > hoy.getMonthValue()) {
                return LocalDate.now().getYear() - fechaNacimiento.getYear() - 1;
            }
        }

        return edad();
    }

    /**
     * Para saber el día de la semana de nacimiento.
     *
     * @return El día de la semana de nacimiento.
     */
    public String diaSemanaNacimiento() {
        return diaSemanaNacimiento(false);
    }

    /**
     * Muestra el día de la semana (en español en inglés).
     * @param enEspañol True para mostrarlo en español.
     * @return El día de la semana de nacimiento.
     */
    public String diaSemanaNacimiento(boolean enEspañol) {
        if (enEspañol) {
            return diaSemanaNacimientoES();
        }
        return fechaNacimiento.getDayOfWeek().toString();
    }

    private static final String[] diasSemana =
            {"LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES", "SÁBADO", "DOMINGO"};

    /**
     * Devuelve el día de la semana de nacimiento en español.
     *
     * @return El día de la semana de nacimiento en español.
     */
    public String diaSemanaNacimientoES() {
        return diasSemana[fechaNacimiento.getDayOfWeek().getValue() - 1];
    }

    /**
     * Mostrar los datos por la consola de esta persona.
     *
     * @param enEspañol True si se muestra el día de la semana de nacimiento en español.
     */
    public void mostrarDatos(boolean enEspañol) {
//        System.out.printf("Nombre: %s\n", nombre);
//        System.out.printf("Apellidos: %s\n", apellidos);
        System.out.printf("Nombre y apellidos: %s %s\n", nombre, apellidos);
        System.out.printf("Fecha nacimiento: %s (%s)\n", fechaNacimiento, diaSemanaNacimiento(enEspañol));
        //System.out.printf("Día de la semana de nacimiento: %s\n", diaSemanaNacimiento(enEspañol));
        System.out.printf("Edad actual: %s (en este año: %s)\n", edadActual(), edad());
        //System.out.printf("Edad (en este año): %s\n", edad());
    }

    public static void main(String[] args) {
        Persona carapapa = new Persona("Guillermo", "Som Cerezo", LocalDate.of(1957,6,7));

        System.out.printf("La persona carapapa: %s\n", carapapa);

        // Para acceder a las "propiedades" del record hay que usar el método público definido automáticamente.
        System.out.printf("El nombre de carapapa es %s\n", carapapa.nombre());

        // Solo se podría usar si el record está definido dentro de la clase que lo accede.
        // O se accede desde este método definido en el propio record.
        System.out.printf("El nombre de carapapa es %s\n", carapapa.nombre);
    }
}
