import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Fichero8 {

    // Función para listar contenido de una carpeta
    public static void explorarCarpeta(String ruta) {
        File carpeta = new File(ruta);

        if (!carpeta.exists()) {
            System.out.println("✗ La ruta no existe: " + ruta);
            return;
        }

        // Mostrar contenido si es directorio
        if (carpeta.isDirectory()) {
            String[] elementos = carpeta.list();
            System.out.println("Explorando: " + ruta);

            int total = 0;
            for (String el : elementos) {
                File actual = new File(carpeta, el);
                analizarElemento(actual);
                total++;
            }
            System.out.println("\nTotal de elementos encontrados: " + total);
        } else {
            // Si es archivo, solo mostrar tamaño y nombre
            analizarElemento(carpeta);
            System.out.println("\nTotal de elementos encontrados: 1");
        }
    }

    // Muestra nombre, tipo y tamaño/número de elementos
    public static void analizarElemento(File elemento) {
        if (elemento.isDirectory()) {
            String[] lista = elemento.list();
            int cantidad = lista != null ? lista.length : 0;
            System.out.println("- " + elemento.getName() + " [DIRECTORIO - " + cantidad + " elementos]");
        } else if (elemento.isFile()) {
            long tam = elemento.length();
            System.out.println("- " + elemento.getName() + " [ARCHIVO - " + tam + " bytes]");
        }
    }

    // Convierte ruta a URI y muestra equivalencia
    public static void convertirAURI(String ruta) {
        try {
            File file = new File(ruta);
            URI uri = file.toURI();
            System.out.println("\nCONVERSIÓN A URI:");
            System.out.println("Ruta original: " + ruta);
            System.out.println("URI equivalente: " + uri.toString());

            // Comprobación básica si el file apunta al mismo elemento
            File fileUri = new File(uri);
            if (fileUri.exists()) {
                System.out.println("✓ La URI es válida y apunta al mismo elemento");
            } else {
                System.out.println("✗ Hay un problema con la conversión a URI");
            }
        } catch (Exception e) {
            System.out.println("Error al convertir a URI: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== EXPLORADOR INTELIGENTE ===");
        System.out.print("Introduce la ruta a explorar: ");
        String ruta = sc.nextLine();

        explorarCarpeta(ruta);
        convertirAURI(ruta);
    }
}
