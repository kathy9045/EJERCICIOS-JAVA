import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Contacto {
    private String nombre;
    private String telefono;
    private String email;

    public Contacto(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

public class GestorContactos {
    private List<Contacto> listaContactos;

    public GestorContactos() {
        this.listaContactos = new ArrayList<>();
    }

    public void agregarContacto(String nombre, String telefono, String email) {
        Contacto nuevoContacto = new Contacto(nombre, telefono, email);
        listaContactos.add(nuevoContacto);
        System.out.println("Contacto agregado.");
    }

    public void eliminarContacto(String nombre) {
        Contacto contactoAEliminar = null;
        for (Contacto contacto : listaContactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre)) {
                contactoAEliminar = contacto;
                break;
            }
        }
        if (contactoAEliminar != null) {
            listaContactos.remove(contactoAEliminar);
            System.out.println("Contacto eliminado.");
        } else {
            System.out.println("No se encontró el contacto.");
        }
    }

    public void buscarContacto(String nombre) {
        boolean encontrado = false;
        for (Contacto contacto : listaContactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println(contacto);
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            System.out.println("No se encontró el contacto.");
        }
    }

    public void mostrarContactos() {
        if (listaContactos.isEmpty()) {
            System.out.println("No hay contactos para mostrar.");
        } else {
            System.out.println("Lista de contactos:");
            for (Contacto contacto : listaContactos) {
                System.out.println(contacto);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorContactos gestor = new GestorContactos();

        System.out.println("Bienvenido");

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Eliminar contacto");
            System.out.println("3. Buscar contacto por nombre");
            System.out.println("4. Mostrar todos los contactos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del contacto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Teléfono del contacto: ");
                    String telefono = scanner.nextLine();
                    System.out.print("Email del contacto: ");
                    String email = scanner.nextLine();
                    gestor.agregarContacto(nombre, telefono, email);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del contacto para eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    gestor.eliminarContacto(nombreEliminar);
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del contacto para buscar: ");
                    String nombreBuscar = scanner.nextLine();
                    gestor.buscarContacto(nombreBuscar);
                    break;
                case 4:
                    gestor.mostrarContactos();
                    break;
                case 5:
                    System.out.println("Cerrado");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor seleccione una opción del 1 al 5.");
            }
        }
    }
}
