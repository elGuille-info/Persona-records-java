# Persona-records-java
Crear records Persona, guardarlas en un ArrayList y leer y guardar en un fichero de texto.


Datos de personas y salida y entrada en un fichero. (27/nov/22 19.12)<br>
<br>
Este proyecto utiliza un record para los datos de una persona.<br>
Lee los datos del fichero (si existe).<br>
Pide más datos para añadir.<br>
Guarda los datos en el fichero.<br>
<br>
Como argumento de la línea de comando se puede indicar el fichero de datos (path completo o parcial).<br>
Si no se indica, se usa el predeterminado.<br>
Si no existe el fichero, se intenta crear el directorio donde se guardará.<br>
<br>

<br>

**Nota:**<br>
Crear una clase Persona de tipo record está bien si los campos siempre serán fijos.<br>
Si cualquiera de las "propiedades" pueden cambiar, debería ser de tipo class.<br>
<br>
En este ejemplo, los campos son nombre, apellidos y fecha de nacimiento.<br>
Realmente, tampoco sería válido para record, ya que una persona puede cambiar legalmente su nombre y apellidos, aunque no la fecha de nacimiento.<br>
<br>
Pero para probar los records y guardarlo en una colección y fichero es válido.<br>

