package calculadora.java;

import java.util.Scanner;

public class calculadora {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Calculadora de expresiones matemáticas");
        System.out.println("Signos permitidos: +, -, *, /, ()");
        System.out.println("Ingrese 'salir' para cerrar la calculadora");

        while (true) {
            System.out.print("\nIngrese una expresión matemática: ");
            String expresion = scanner.nextLine();

            if (expresion.equalsIgnoreCase("salir")) {
                break;
            }

            try {
                double resultado = evaluarExpresion(expresion);
                System.out.println("Resultado: " + resultado);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    public static double evaluarExpresion(String expresion) {
        expresion = expresion.replaceAll("\\s+", "");
        char[] tokens = expresion.toCharArray();
        return evaluarExpresion(tokens, 0)[0];
    }

    private static double[] evaluarExpresion(char[] tokens, int indice) {
        double[] resultados = new double[2];
        char operador = '+';
        while (indice < tokens.length) {
            char token = tokens[indice];
            if (Character.isDigit(token) || token == '.') {
                StringBuilder sb = new StringBuilder();
                while (indice < tokens.length && (Character.isDigit(tokens[indice]) || tokens[indice] == '.')) {
                    sb.append(tokens[indice++]);
                }
                double numero = Double.parseDouble(sb.toString());
                resultados[0] = operar(resultados[0], numero, operador);
                continue;
            }
            if (token == '(') {
                indice++;
                resultados = evaluarExpresion(tokens, indice);
                resultados[0] = operar(resultados[0], resultados[1], operador);
                continue;
            }
            if (token == ')') {
                break;
            }
            if (token == '+' || token == '-' || token == '*' || token == '/') {
                operador = token;
                indice++;
                continue;
            }

            throw new IllegalArgumentException("NO RECONOCIDO: " + token);
        }

        return resultados;
    }

    private static double operar(double a, double b, char operador) {
        switch (operador) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new IllegalArgumentException("División por cero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("NO VALIDO: " + operador);
        }
    }
}