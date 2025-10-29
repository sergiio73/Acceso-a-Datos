import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Fichero7 {

    public static void organizarBiblioteca(String categoria) {
        File dirCategoria = new File("C:\\Users\\AlumnoAfternoon\\Documents\\Pruebas Java\\" + categoria);
        File catalogo = new File(dirCategoria, "catalogo.txt");
        try {
            if (!dirCategoria.exists()) {
                dirCategoria.mkdir();
                System.out.println("Categoría creada: " + dirCategoria.getAbsolutePath());
            } else {
                System.out.println("La categoría ya existe.");
            }
            if (!catalogo.exists()) {
                catalogo.createNewFile();
                System.out.println("Archivo catalogo.txt creado.");
            } else {
                System.out.println("El archivo catalogo.txt ya existe.");
            }
        } catch(IOException e) {
            System.out.println("Error al crear archivo: " + e.getMessage());
        }
    }


    public static void verificarLibro(String categoria, String libro) {
        File dirCategoria = new File("C:\\Users\\AlumnoAfternoon\\Documents\\Pruebas Java\\" + categoria);
        File libroFile = new File(dirCategoria, libro + ".txt");
        Scanner sc = new Scanner(System.in);
        if (libroFile.exists()) {
            System.out.println("El libro ya existe: " + libroFile.getAbsolutePath());
        } else {
            System.out.println("El libro no existe. ¿Quieres crearlo? (s/n)");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                try {
                    libroFile.createNewFile();
                    System.out.println("Libro creado correctamente.");
                } catch (IOException e) {
                    System.out.println("Error al crear libro: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce la categoría: ");
        String categoria = sc.nextLine();
        organizarBiblioteca(categoria);

        System.out.print("Introduce el nombre del libro: ");
        String libro = sc.nextLine();
        verificarLibro(categoria, libro);
    }
}
