package tarea_5;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Alumno implements Serializable {

	private static final long serialVersionUID = 2180170612359928850L;

	// Constante para indicar el número de alumnos
	private static final int NUMERO_DE_ALUMNOS = 5;

	// Scanner declarado como static para no tener que cerrarlo:
	private static Scanner sc = new Scanner(System.in);

	// Enumerado para elegir el criterio de ordenación:
	private enum Criterio {
		NIA, NOMBRE, APELLIDOS, GENERO, FECHA_NACIMIENTO, CICLO, CURSO, GRUPO
	};

	// Atributos privados de la clase Alumno
	private int nia = 0;
	private String nombre;
	private String apellidos;
	private char genero = 'S';
	private Date fechaNacimiento;
	private String ciclo;
	private String curso;
	private String grupo;

	// Constructores de la clase Alumno
	public Alumno() {
	}

	public Alumno(int nia, String nombre, String apellidos, char genero, Date fechaNacimiento, String ciclo,
			String curso, String grupo) {

		this.nia = nia;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.ciclo = ciclo;
		this.curso = curso;
		this.grupo = grupo;
	}

	// Getters & Setters:
	public int getNia() {
		return nia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	@Override
	public String toString() {
		return "NIA: " + nia + " --> " + nombre + ", " + apellidos + " - Género: " + genero + " - Fecha de Nacimiento: "
				+ fechaNacimiento + " - Ciclo: " + ciclo + " - Curso: " + curso + " Grupo: " + grupo;
	}

	/**
	 * MÉTODO para crear un fichero BINARIO solicitado por teclado
	 * 
	 * @author Alberto Polo
	 * @return devuelve un fichero binario
	 */
	public File creaFicheroBinario() {

		String nombreFicheroBinario, ruta = "";
		File ficheroBinario = null;

		while (ficheroBinario == null) {
			System.out.print("Introduzca el NOMBRE del fichero BINARIO donde desee almacenar " + NUMERO_DE_ALUMNOS
					+ " alumnos: ");
			nombreFicheroBinario = sc.nextLine();

			System.out.println("Introduzca la RUTA del fichero BINARIO llamado: " + nombreFicheroBinario
					+ " donde desee almacenar " + NUMERO_DE_ALUMNOS + " alumnos.");
			System.out.print("(Por ejemplo: \\src\\tarea_5\\): ");
			sc.next();
			ruta = sc.nextLine();

			ficheroBinario = new File(nombreFicheroBinario, ruta);

			if (!ficheroBinario.exists()) {
				try {
					ficheroBinario.createNewFile();
					System.out.println("----------------------------------------------------------------------");
					System.out.println(
							"Fichero binario llamado - " + ficheroBinario.getName() + " - creado correctamente");
					System.out.println("----------------------------------------------------------------------");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {

				System.out.println("El fichero: " + ficheroBinario.getName() + " ya existe en la ruta: "
						+ ficheroBinario.getAbsolutePath());

				ficheroBinario = null;

				System.out.println("-----------------------------------------------------------");
				System.out.println("Por favor, introduzca un fichero que no exista previamente.");
				System.out.println("-----------------------------------------------------------");
			}
		}
		return ficheroBinario;
	}

	/**
	 * MÉTODO para leer 1 alumno, crear el OBJETO alumno y guardarlo en un fichero
	 * BINARIO solicitado por teclado
	 * 
	 * @author Alberto Polo
	 * @param ficheroBinario - Recibe un fichero Binario
	 */
	public void guarda_Objeto_Alumno_en_Fichero_Binario(File ficheroBinario) {

		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		for (int i = 0; i < NUMERO_DE_ALUMNOS; i++) {

			// NIA - int
			// Vamos añadiendo el nia secuencialmente incrementándolo en 1 unidad:
			nia = i + 1;

			// NOMBRE - String
			System.out.print("Introduzca el NOMBRE del alumno " + (i + 1) + ": ");
			nombre = sc.nextLine().toUpperCase();

			// APELLIDOS - String
			System.out.print("Introduzca los APELLIDOS del alumno " + (i + 1) + ": ");
			apellidos = sc.nextLine().toUpperCase();

			// GÉNERO - char

			do {
				System.out.print("Introduzca el GÉNERO del alumno(H/M): ");
				String entradaTeclado = sc.nextLine().toUpperCase();

				if (entradaTeclado.length() > 0) {
					genero = entradaTeclado.charAt(0);
				} else {
					genero = ' ';
				}

			} while (genero != 'H' && genero != 'M');

			// FECHA DE NACIMIENTO - Date
			System.out.print("Introduzca la FECHA DE NACIMIENTO del alumno en formato (dd/MM/yyyy): ");
			String fechaNacimientoString = sc.nextLine();

			// Definir formato de fecha según el String:
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

			try {
				// Convertir el String en Date
				fechaNacimiento = formato.parse(fechaNacimientoString);
				// System.out.println("Fecha convertida: " + fecha);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// CICLO - String
			System.out.print("Introduzca el CICLO del alumno " + (i + 1) + ": ");
			ciclo = sc.nextLine().toUpperCase();

			// CURSO - String
			System.out.print("Introduzca el CURSO del alumno " + (i + 1) + ": ");
			curso = sc.nextLine().toUpperCase();

			// GRUPO - String
			System.out.print("Introduzca el GRUPO del alumno " + (i + 1) + ": ");
			grupo = sc.nextLine().toUpperCase();
			System.out.println("----------------------------------------------------------------------");

			try {

				/*
				 * MODO APPEND: El FileOutputStream se crea con el segundo argumento "true", que
				 * habilita el modo de añadir (append). Esto evita que el fichero se
				 * sobrescriba.
				 */

				fos = new FileOutputStream(ficheroBinario, true); // <<--- Modo append

				/*
				 * Encabezado del stream: Al abrir el ObjectOutputStream, si el fichero no está
				 * vacío, se sobrescribe el método writeStreamHeader para evitar escribir un
				 * nuevo encabezado, ya que el encabezado solo debe escribirse una vez al
				 * principio del fichero.
				 */

				if (ficheroBinario.length() == 0) {
					oos = new ObjectOutputStream(fos); // Si el fichero está vacío, crear el ObjectOutputStream
				} else {
					oos = new ObjectOutputStream(fos) {
						protected void writeStreamHeader() throws IOException {
							reset(); // No sobrescribir el encabezado en el modo append
						}
					};
				}

				Alumno alumno = new Alumno(nia, nombre, apellidos, genero, fechaNacimiento, ciclo, curso, grupo);
				oos.writeObject(alumno);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (oos != null) {
					try {
						oos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Se han leído los datos de " + NUMERO_DE_ALUMNOS + " alumnos.");
		System.out.println("----------------------------------------------------------------------");
	}

	/**
	 * MÉTODO para leer (y mostrar) un fichero binario de alumnos que se solicitará
	 * por teclado:
	 * 
	 * @author Alberto Polo
	 */
	public void leeFicheroDeAlumnos() {

		System.out.println("----------------------------------------------------------------------");
		System.out.println("--------------------- TAREA 5 - PARTE 2 ------------------------------");
		System.out.println("----------------------------------------------------------------------");

		File directorioActual = new File(".");
		System.out.print("Introduzca el NOMBRE del FICHERO de ALUMNOS, ubicado en la ruta: "
				+ directorioActual.getAbsolutePath() + ", que desee visualizar: ");
		File ficheroDeAlumnos = new File(directorioActual, sc.nextLine());

		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {

			fis = new FileInputStream(ficheroDeAlumnos);
			ois = new ObjectInputStream(fis);

			while (fis.available() > 0) {
				Alumno alumno = (Alumno) ois.readObject();

				System.out.println("----------------------------------------------------------------------");
				System.out.println("NIA: " + alumno.nia);
				System.out.println("NOMBRE: " + alumno.nombre);
				System.out.println("APELLIDOS: " + alumno.apellidos);
				System.out.println("GÉNERO: " + alumno.genero);
				System.out.println("FECHA DE NACIMIENTO: " + alumno.fechaNacimiento);
				System.out.println("CICLO: " + alumno.ciclo);
				System.out.println("CURSO: " + alumno.curso);
				System.out.println("GRUPO: " + alumno.grupo);
				System.out.println("----------------------------------------------------------------------");
			}
		} catch (EOFException e) {
			// Esta excepción se lanza cuando se alcanza el final del archivo
			System.out.println("Fin del archivo alcanzado.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}