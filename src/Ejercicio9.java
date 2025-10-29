import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Ejercicio9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            mostrarMenu();
            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    verificarArchivo(sc);
                    break;
                case "2":
                    explorarCarpeta(sc);
                    break;
                case "3":
                    crearCarpeta(sc);
                    break;
                case "4":
                    crearArchivo(sc);
                    break;
                case "5":
                    trabajarConURIs(sc);
                    break;
                case "6":
                    System.out.println("¡Hasta pronto! Gracias por usar el asistente.");
                    return;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    public static void mostrarMenu() {
        System.out.println("\n===============================");
        System.out.println("     MI ASISTENTE DE ARCHIVOS  ");
        System.out.println("===============================");
        System.out.println("1. Verificar si un archivo existe");
        System.out.println("2. Explorar una carpeta");
        System.out.println("3. Crear una nueva carpeta");
        System.out.println("4. Crear un nuevo archivo");
        System.out.println("5. Trabajar con URIs");
        System.out.println("6. Salir");
        System.out.println("===============================");
        System.out.print("Elige una opción: ");
    }


    public static void verificarArchivo(Scanner sc) {
        System.out.println("\n--- VERIFICAR ARCHIVO ---");
        System.out.print("Introduce el directorio padre: ");
        String rutaPadre = sc.nextLine();
        System.out.print("Introduce el nombre del archivo: ");
        String nomArchivo = sc.nextLine();

        File archivo = new File(rutaPadre, nomArchivo);

        if (archivo.exists()) {
            System.out.println("✓ El archivo existe en: " + archivo.getAbsolutePath());
            if (archivo.isDirectory()) {
                System.out.println("Es un directorio");
            } else if (archivo.isFile()) {
                System.out.println("Es un archivo de " + archivo.length() + " bytes");
            }
        } else {
            System.out.println("✗ El archivo no existe: " + archivo.getAbsolutePath());
        }
        System.out.println("\nPresiona Enter para continuar...");
        sc.nextLine();
    }


    public static void explorarCarpeta(Scanner sc) {
        System.out.println("\n--- EXPLORAR DIRECTORIO ---");
        System.out.print("Introduce la ruta del directorio: ");
        String ruta = sc.nextLine();
        File carpeta = new File(ruta);

        if (!carpeta.exists()) {
            System.out.println("✗ La ruta no existe.");
        } else if (!carpeta.isDirectory()) {
            System.out.println("✗ La ruta no corresponde a un directorio");
        } else {
            String[] contenido = carpeta.list();
            System.out.println("Contenido del directorio:");

            int total = 0;
            for (String nombre : contenido) {
                System.out.println((++total) + ". " + nombre);
            }
            System.out.println("\nTotal: " + total + " elementos");
        }
        System.out.println("\nPresiona Enter para continuar...");
        sc.nextLine();
    }


    public static void crearCarpeta(Scanner sc) {
        System.out.println("\n--- CREAR CARPETA ---");
        System.out.print("Introduce la ruta de la nueva carpeta: ");
        String ruta = sc.nextLine();
        File carpeta = new File(ruta);

        if (carpeta.exists()) {
            System.out.println("▲ La carpeta ya existe en: " + carpeta.getAbsolutePath());
        } else {
            if (ruta.endsWith(".txt")) {
                System.out.println("✗ Parece que has introducido una ruta de archivo, no de carpeta.");
            } else if (carpeta.mkdirs()) {
                System.out.println("✓ Carpeta creada exitosamente en: " + carpeta.getAbsolutePath());
            } else {
                System.out.println("✗ No se pudo crear la carpeta.");
            }
        }
        System.out.println("\nPresiona Enter para continuar...");
        sc.nextLine();
    }


    public static void crearArchivo(Scanner sc) {
        System.out.println("\n--- CREAR ARCHIVO ---");
        System.out.print("Introduce la ruta completa del nuevo archivo: ");
        String ruta = sc.nextLine();
        File archivo = new File(ruta);
        File carpeta = archivo.getParentFile();

        try {
            if (archivo.exists()) {
                System.out.println("▲ El archivo ya existe: " + archivo.getAbsolutePath());
            } else {
                if (!carpeta.exists()) {
                    if (carpeta.mkdirs()) {
                        System.out.println("✓ Estructura de carpetas creada.");
                    }
                }
                if (archivo.createNewFile()) {
                    System.out.println("✓ Archivo creado exitosamente en: " + archivo.getAbsolutePath());
                } else {
                    System.out.println("✗ No se pudo crear el archivo.");
                }
            }
        } catch (IOException e) {
            System.out.println("✗ Error al crear archivo: " + e.getMessage());
        }
        System.out.println("\nPresiona Enter para continuar...");
        sc.nextLine();
    }


    public static void trabajarConURIs(Scanner sc) {
        System.out.println("\n--- TRABAJAR CON URIs ---");
        System.out.println("Elige una opción:");
        System.out.println("1. Verificar una URI existente");
        System.out.println("2. Convertir ruta a URI");
        System.out.print("Tu elección: ");
        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                System.out.print("Introduce la URI (ejemplo: file:///C:/ruta/archivo.txt): ");
                String uriInput = sc.nextLine();
                try {
                    URI uri = new URI(uriInput);
                    File fileUri = new File(uri);
                    if (fileUri.exists()) {
                        System.out.println("✓ La URI representa un " + (fileUri.isDirectory() ? "directorio" : "archivo") + " en: " + uri.toString());
                    } else {
                        System.out.println("✗ La URI no existe: " + uri.toString());
                    }
                } catch (URISyntaxException e) {
                    System.out.println("✗ Error en la sintaxis de la URI: " + e.getMessage());
                }
                break;
            case "2":
                System.out.print("Introduce la ruta a convertir: ");
                String ruta = sc.nextLine();
                File file = new File(ruta);
                URI uri = file.toURI();
                System.out.println("Ruta original: " + ruta);
                System.out.println("URI generada: " + uri.toString());
                File fileUri = new File(uri);
                if (fileUri.exists()) {
                    System.out.println("✓ La ruta existe y la URI es válida");
                } else {
                    System.out.println("▲ La ruta no existe, pero la URI es válida");
                }
                break;
            default:
                System.out.println("Opción no válida");
        }
        System.out.println("\nPresiona Enter para continuar...");
        sc.nextLine();
    }
}
