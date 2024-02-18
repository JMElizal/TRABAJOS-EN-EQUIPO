import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SumaVectores {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Vector3D> vectores = new HashMap<>();

        // Solicitar al usuario ingresar los nombres y los valores para los vectores
        while (true) {
            System.out.println("Ingrese el nombre del vector o escriba 'fin' para terminar:");
            String nombre = scanner.next();
            if (nombre.equalsIgnoreCase("fin")) {
                break;
            } else {
                Vector3D vector = pedirVector(scanner, nombre);
                vectores.put(nombre, vector);
            }
        }

        // Calcular la suma de los vectores ingresados
        Vector3D suma = null;
        for (Vector3D vector : vectores.values()) {
            if (suma == null) {
                suma = new Vector3D(vector);
            } else {
                suma = suma.suma(vector);
            }
        }

        // Imprimir el resultado
        if (suma != null) {
            System.out.println("La suma de los vectores ingresados es: " + suma);
        } else {
            System.out.println("No se ingresaron vectores.");
        }

        scanner.close();
    }

    // Método para solicitar al usuario ingresar los nombres y los valores para un vector
    private static Vector3D pedirVector(Scanner scanner, String nombre) {
        System.out.println("Ingrese los nombres y los valores para el vector " + nombre + ":");
        System.out.print("Nombre para la primera componente de " + nombre + ": ");
        String nombre1 = scanner.next();
        double valor1 = obtenerValor(scanner, nombre1);
        System.out.print("Nombre para la segunda componente de " + nombre + ": ");
        String nombre2 = scanner.next();
        double valor2 = obtenerValor(scanner, nombre2);
        System.out.print("Nombre para la tercera componente de " + nombre + ": ");
        String nombre3 = scanner.next();
        double valor3 = obtenerValor(scanner, nombre3);
        return new Vector3D(nombre, nombre1, valor1, nombre2, valor2, nombre3, valor3);
    }

    // Método para obtener el valor del componente, interpretando letras como 1 si coinciden con el nombre del componente
    private static double obtenerValor(Scanner scanner, String nombre) {
        System.out.print("Valor para " + nombre + ": ");
        if (scanner.hasNextDouble()) {
            return scanner.nextDouble();
        } else {
            String letra = scanner.next();
            if (letra.equalsIgnoreCase(nombre)) {
                return 1; // Asignar 1 si la letra ingresada coincide con el nombre del componente
            } else {
                System.out.println("Valor no válido para " + nombre + ", ingrese un valor numérico o la letra correspondiente.");
                return obtenerValor(scanner, nombre);
            }
        }
    }
}

class Vector3D {
    private String nombre;
    private double valor1;
    private String nombre1;
    private double valor2;
    private String nombre2;
    private double valor3;
    private String nombre3;

    public Vector3D(String nombre) {
        this.nombre = nombre;
    }

    // Constructor utilizado para copiar los nombres de las componentes
    public Vector3D(Vector3D otro) {
        this.nombre = otro.nombre;
        this.nombre1 = otro.nombre1;
        this.valor1 = otro.valor1;
        this.nombre2 = otro.nombre2;
        this.valor2 = otro.valor2;
        this.nombre3 = otro.nombre3;
        this.valor3 = otro.valor3;
    }

    public Vector3D(String nombre, String nombre1, double valor1, String nombre2, double valor2, String nombre3, double valor3) {
        this.nombre = nombre;
        this.nombre1 = nombre1;
        this.valor1 = valor1;
        this.nombre2 = nombre2;
        this.valor2 = valor2;
        this.nombre3 = nombre3;
        this.valor3 = valor3;
    }

    public Vector3D suma(Vector3D otro) {
        double nuevoValor1 = this.valor1 + otro.valor1;
        double nuevoValor2 = this.valor2 + otro.valor2;
        double nuevoValor3 = this.valor3 + otro.valor3;
        return new Vector3D("Suma", nombre1, nuevoValor1, nombre2, nuevoValor2, nombre3, nuevoValor3);
    }

    @Override
    public String toString() {
        return valor1 + "(" + nombre1 + ") + " + valor2 + "(" + nombre2 + ") + " + valor3 + "(" + nombre3 + ")";
    }
}
