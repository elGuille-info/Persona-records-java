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

/**
 * Record Persona para almacenar el nombre, los apellidos y la fecha de nacimiento.<br>
 * Con métodos para saber la edad actual, el día de la semana en que nació y mostrar por consola los datos.
 *
 * @param nombre El nombre de esta persona.
 * @param apellidos Los apellidos de esta persona.
 * @param fechaNacimiento La fecha de nacimiento de esta persona.
 */
public record Persona(String nombre, String apellidos, LocalDate fechaNacimiento) {

    // El nombre de este método debería ser edadEsteAño, pero la ñ no será compatible.

    /**
     * Devuelve la edad (en este año) de esta persona.
     *
     * @return Un entero con la diferencia del año actual menos el año de nacimiento.
     */
    private int edadThisYear() {
        return LocalDate.now().getYear() - fechaNacimiento.getYear();
    }

    /**
     * La edad actual teniendo en cuenta si ha cumplido años en este año.
     *
     * @return La edad actual.
     */
    public int edad() {
        var hoy = LocalDate.now();
        // Si el mes de la fecha de nacimiento es mayor del actual, aún no ha cumplido años.
        if (fechaNacimiento.getMonthValue() > hoy.getMonthValue()) {
            return edadThisYear() -1;
        }
        // Si es el mismo mes, pero el día de nacimiento es posterior, aún no ha cumplido años.
        else if (fechaNacimiento.getMonthValue() == hoy.getMonthValue()) {
            if (fechaNacimiento.getDayOfMonth() > hoy.getDayOfMonth()) {
                return edadThisYear() - 1;
            }
        }

        return edadThisYear();
    }

    /**
     * Para saber el día de la semana de nacimiento en inglés.
     *
     * @return El día de la semana de nacimiento.
     */
    public String diaSemanaNacimiento() {
        return fechaNacimiento.getDayOfWeek().toString();
    }

    /**
     * Muestra el día de la semana (en español en inglés).
     * @param inSpanish True para mostrarlo en español.
     * @return El día de la semana de nacimiento.
     */
    public String diaSemanaNacimiento(boolean inSpanish) {
        if (inSpanish) {
            return diaSemanaNacimientoES();
        }
        return diaSemanaNacimiento();
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
     * @param inSpanish True si se muestra el día de la semana de nacimiento en español.
     */
    public void mostrarDatos(boolean inSpanish) {
        System.out.printf("Nombre y apellidos: %s %s\n", nombre, apellidos);
        System.out.printf("Fecha nacimiento: %s (%s)\n", fechaNacimiento, diaSemanaNacimiento(inSpanish));
        System.out.printf("Edad actual: %s (en este año: %s)\n", edad(), edadThisYear());
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
