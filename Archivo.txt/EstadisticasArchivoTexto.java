import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EstadisticasArchivoTexto {

    public static void main(String[] args) {
        String nombreArchivo = "texto.txt";
        try {
            File archivo = new File(nombreArchivo);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            br.close();
            fr.close();

            String texto = contenido.toString();
            int totalPalabras = contarPalabras(texto);
            int totalOraciones = contarOraciones(texto);
            Map<String, Integer> frecuenciaPalabras = contarFrecuenciaPalabras(texto);
            String palabraMasFrecuente = encontrarPalabraMasFrecuente(frecuenciaPalabras);
            double longitudPromedioPalabras = calcularLongitudPromedio(texto);

            System.out.println("Estadísticas del archivo: " + nombreArchivo);
            System.out.println("Número total de palabras: " + totalPalabras);
            System.out.println("Número total de oraciones: " + totalOraciones);
            System.out.println("Palabra más frecuente: " + palabraMasFrecuente);
            System.out.printf("Longitud promedio de las palabras: %.2f\n", longitudPromedioPalabras);

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static int contarPalabras(String texto) {
        String[] palabras = texto.split("\\s+");
        return palabras.length;
    }

    private static int contarOraciones(String texto) {
        String[] oraciones = texto.split("[.!?]+");
        return oraciones.length;
    }

    private static Map<String, Integer> contarFrecuenciaPalabras(String texto) {
        Map<String, Integer> frecuenciaPalabras = new HashMap<>();
        String[] palabras = texto.split("\\s+");
        for (String palabra : palabras) {
            palabra = palabra.toLowerCase().replaceAll("[^a-zA-Z]", "");
            if (!palabra.isEmpty()) {
                frecuenciaPalabras.put(palabra, frecuenciaPalabras.getOrDefault(palabra, 0) + 1);
            }
        }
        return frecuenciaPalabras;
    }

    private static String encontrarPalabraMasFrecuente(Map<String, Integer> frecuenciaPalabras) {
        String palabraMasFrecuente = "";
        int frecuenciaMaxima = 0;
        for (Map.Entry<String, Integer> entry : frecuenciaPalabras.entrySet()) {
            if (entry.getValue() > frecuenciaMaxima) {
                palabraMasFrecuente = entry.getKey();
                frecuenciaMaxima = entry.getValue();
            }
        }
        return palabraMasFrecuente + " (aparece " + frecuenciaMaxima + " veces)";
    }

    private static double calcularLongitudPromedio(String texto) {
        String[] palabras = texto.split("\\s+");
        int totalCaracteres = 0;
        for (String palabra : palabras) {
            palabra = palabra.replaceAll("[^a-zA-Z]", "");
            totalCaracteres += palabra.length();
        }
        return (double) totalCaracteres / palabras.length;
    }
}