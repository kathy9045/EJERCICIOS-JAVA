import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CrearArchivoTexto {

    public static void main(String[] args) {
        String nombreArchivo = "texto.txt";
        String contenido = "Este es un ejemplo de texto para comprobar.\n" +
                "Contiene varias palabras y oraciones para verificar .\n" +
                "etc.";

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivo)))) {
            writer.println(contenido);
            System.out.println("Archivo " + nombreArchivo + " creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }

        EstadisticasArchivoTexto.main(args);
    }
}