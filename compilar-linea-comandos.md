# Cómo compilar y ejecutar desde la línea de comandos.

> **NOTA:**
> Esto solo es una recomandación de cómo hacerlo.


```
rem Abrir una venta de comandos (o la terminal)
rem Usar cada comando de forma separada.

rem asignar las variables del nombre de la clase y de direcorio con el código

set clase_compilar=PersonaMain
set dir_java=Prueba-records

rem posicionarse en la unidad de trabajo
e:

rem Posicionarse en el directorio
cd E:\gsCodigo_00\Java\pruebas\compilar\%dir_java%


rem la variable de entorno %JAVA_HOME19% debe apuntar al SDK a usar,
rem en mi caso sería "C:\Users\Guille\.jdks\openjdk-19.0.1"


rem compilar el código en la carpeta actual
%JAVA_HOME19%\bin\javac -d .\ -encoding UTF-8 *.java

rem compilar el código que está en la carpeta de las pruebas
%JAVA_HOME19%\bin\javac -d .\ -encoding UTF-8 ..\..\%dir_java%\src\*.java


rem crear el manifiesto:
echo Main-Class: %clase_compilar% > manifiesto.txt
```
---

```
rem con manifiesto
%JAVA_HOME19%\bin\jar cvmf manifiesto.txt %clase_compilar%.jar *.class


rem ejecutar el jar (ejecutable) usando el fichero de datos predeterminado
%JAVA_HOME19%\bin\java -jar %clase_compilar%.jar

rem indicando el fichero de datos
%JAVA_HOME19%\bin\java -jar %clase_compilar%.jar ./out/Personas.txt

%JAVA_HOME19%\bin\java -jar %clase_compilar%.jar ./out/Personas2.txt
```

---

```
rem sin manifiesto
%JAVA_HOME19%\bin\jar cf %clase_compilar%.jar *.class

rem ejecutar sin manifiesto
%JAVA_HOME19%\bin\java -cp %clase_compilar%.jar %clase_compilar%
```
---

```
rem Ejecutar el método main de %clase_compilar% sin necesidad de que esté el fichero .jar, pero sí los .class
%JAVA_HOME19%\bin\java %clase_compilar%

```
