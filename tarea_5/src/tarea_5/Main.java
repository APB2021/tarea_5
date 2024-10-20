package tarea_5;

import java.io.File;

public class Main {

	public static void main(String[] args) {

		/*
		 * ------------------------ Tarea 5 - Octubre - 2024 ------------------------
		 * 
		 * 
		 * Realiza un programa Java que lea los datos de 5 alumnos, CREE el OBJETO
		 * alumno, y guarde los alumnos en un fichero binario que solicitará por
		 * teclado.
		 * 
		 * Cada alumno leído deberá ser guardado antes de solicitar el siguiente.
		 * 
		 * Para cada alumno se tiene la siguiente información:
		 * 
		 * NIA (entero), Nombre (String), Apellidos (String), Genero (Char), Fecha de
		 * Nacimiento (Fecha), Ciclo (String), Curso (String), Grupo (String).
		 */

		Alumno alumno = new Alumno();

		File ficheroBinario = alumno.creaFicheroBinario();
		alumno.guarda_Objeto_Alumno_en_Fichero_Binario(ficheroBinario);

		/*
		 * ------------------------ Tarea 5 - Octubre - 2024 ------------------------
		 * ------------------------ Parte 2 -----------------------------------------
		 * 
		 * Crea otro programa Java que lea alumnos de un fichero de alumnos que se
		 * pedirá por teclado, y mostrará los alumnos por pantalla.
		 */

		alumno.leeFicheroDeAlumnos();
	}
}