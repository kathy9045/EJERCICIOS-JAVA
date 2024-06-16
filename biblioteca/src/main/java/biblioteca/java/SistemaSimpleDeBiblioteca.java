package biblioteca.java;

import java.util.ArrayList;
import java.util.Scanner;

class Libro {
    private String titulo;
    private String autor;
    private String ISBN;
    private int añoPublicado;
    private boolean disponible;

    public Libro(String titulo, String autor, String ISBN, int añoPublicado) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.añoPublicado = añoPublicado;
        this.disponible = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getAñoPublicado() {
        return añoPublicado;
    }

    public boolean estaDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String toString() {
        return String.format("%s por %s (%d), ISBN: %s, Disponible: %s",
                titulo, autor, añoPublicado, ISBN, disponible ? "Sí" : "No");
    }
}

class Biblioteca {
    private ArrayList<Libro> libros = new ArrayList<>();

    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println("Libro '" + libro.getTitulo() + "' agregado correctamente.");
    }

    public ArrayList<Libro> buscarLibros(String query) {
        ArrayList<Libro> resultado = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(query.toLowerCase()) ||
                    libro.getAutor().toLowerCase().contains(query.toLowerCase())) {
                resultado.add(libro);
            }
        }
        return resultado;
    }

    public void mostrarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la biblioteca.");
        } else {
            System.out.println("Todos los libros en la biblioteca:");
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    public void prestarLibro(String ISBN) {
        boolean encontrado = false;
        for (Libro libro : libros) {
            if (libro.getISBN().equals(ISBN)) {
                if (libro.estaDisponible()) {
                    libro.setDisponible(false);
                    System.out.println("Libro '" + libro.getTitulo() + "' ha sido prestado.");
                } else {
                    System.out.println("Libro '" + libro.getTitulo() + "' no está disponible actualmente.");
                }
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Libro no encontrado.");
        }
    }

    public void devolverLibro(String ISBN) {
        boolean encontrado = false;
        for (Libro libro : libros) {
            if (libro.getISBN().equals(ISBN)) {
                if (!libro.estaDisponible()) {
                    libro.setDisponible(true);
                    System.out.println("Libro '" + libro.getTitulo() + "' ha sido devuelto.");
                } else {
                    System.out.println("Libro '" + libro.getTitulo() + "' ya está disponible.");
                }
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Libro no encontrado.");
        }
    }
}

public class SistemaSimpleDeBiblioteca {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.agregarLibro(new Libro("Cazadores de sombras", "Cassandra Clare", "9788408083801", 2009));
        biblioteca.agregarLibro(new Libro("Still with you", "Lily del Pilar", "9789584295309", 2021));
        biblioteca.agregarLibro(new Libro("Still with me", "Lily del Pilar", "9789584299215", 2021));
        biblioteca.agregarLibro(new Libro("Maravilloso desastre", "Jamie McGuire", "9786073162715", 2011));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú del Sistema de Gestión de Biblioteca:");
            System.out.println("1. Agregar un nuevo libro");
            System.out.println("2. Buscar un libro");
            System.out.println("3. Mostrar todos los libros");
            System.out.println("4. Prestar un libro");
            System.out.println("5. Devolver un libro");
            System.out.println("6. Salir");

            System.out.print("Ingrese su opción (1-6): ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Ingrese ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Ingrese año de publicación: ");
                    int añoPublicado = scanner.nextInt();
                    scanner.nextLine();

                    Libro nuevoLibro = new Libro(titulo, autor, isbn, añoPublicado);
                    biblioteca.agregarLibro(nuevoLibro);
                    break;
                case 2:
                    System.out.print("Ingrese término de búsqueda: ");
                    String busqueda = scanner.nextLine();
                    ArrayList<Libro> resultadoBusqueda = biblioteca.buscarLibros(busqueda);
                    if (resultadoBusqueda.isEmpty()) {
                        System.out.println("No se encontraron libros que coincidan con su búsqueda.");
                    } else {
                        System.out.println("Resultados de la búsqueda:");
                        for (Libro libro : resultadoBusqueda) {
                            System.out.println(libro);
                        }
                    }
                    break;
                case 3:
                    biblioteca.mostrarLibros();
                    break;
                case 4:
                    System.out.print("Ingrese ISBN del libro a prestar: ");
                    String isbnPrestamo = scanner.nextLine();
                    biblioteca.prestarLibro(isbnPrestamo);
                    break;
                case 5:
                    System.out.print("Ingrese ISBN del libro a devolver: ");
                    String isbnDevolucion = scanner.nextLine();
                    biblioteca.devolverLibro(isbnDevolucion);
                    break;
                case 6:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese un número entre 1 y 6.");
            }
        }
    }
}