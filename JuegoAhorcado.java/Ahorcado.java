import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Ahorcado {

    private static final String[] PALABRAS = { "ANIMAL", "DIA", "CLASE", "LAPIZ", "HORA", "COMIDA", "AMIGOS" };
    private static final int VIDAS_INICIALES = 10;

    private String palabraSecreta;
    private char[] estadoPalabra;
    private List<Character> letrasUsadas;
    private int vidasRestantes;

    public Ahorcado() {
        inicializarJuego();
    }

    private void inicializarJuego() {
        Random random = new Random();
        palabraSecreta = PALABRAS[random.nextInt(PALABRAS.length)].toUpperCase();
        estadoPalabra = new char[palabraSecreta.length()];
        letrasUsadas = new ArrayList<>();
        vidasRestantes = VIDAS_INICIALES;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            estadoPalabra[i] = '_';
        }
    }

    public void jugar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido");
        imprimirEstadoJuego();

        while (vidasRestantes > 0 && !esGanador()) {
            System.out.print("\nIngrese una letra: ");
            String entrada = scanner.nextLine().toUpperCase();

            if (entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))) {
                System.out.println("Por favor ingrese una letra válida.");
                continue;
            }

            char letra = entrada.charAt(0);

            if (letrasUsadas.contains(letra)) {
                System.out.println("Ya se uso esta letra. Inténte de nuevo.");
                continue;
            }

            letrasUsadas.add(letra);

            if (palabraSecreta.contains(String.valueOf(letra))) {
                actualizarEstadoPalabra(letra);
                if (esGanador()) {
                    System.out.println("\n¡GANASTE! Has adivinado la palabra secreta: " + palabraSecreta);
                }
            } else {
                vidasRestantes--;
                System.out.println("Letra incorrecta. Quedan " + vidasRestantes + " vidas.");
                if (vidasRestantes == 0) {
                    System.out.println("PERDISTE. La palabra secreta era: " + palabraSecreta);
                }
            }

            imprimirEstadoJuego();
        }

        scanner.close();
    }

    private void imprimirEstadoJuego() {
        System.out.println("\nVidas restantes: " + vidasRestantes);
        System.out.print("Letras usadas: ");
        for (char letra : letrasUsadas) {
            System.out.print(letra + " ");
        }
        System.out.println("\nPalabra secreta:");
        for (char letra : estadoPalabra) {
            System.out.print(letra + " ");
        }
        System.out.println();
    }

    private void actualizarEstadoPalabra(char letra) {
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                estadoPalabra[i] = letra;
            }
        }
    }

    private boolean esGanador() {
        for (char c : estadoPalabra) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Ahorcado juego = new Ahorcado();
        juego.jugar();
    }
}