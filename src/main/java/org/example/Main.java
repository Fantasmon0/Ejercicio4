package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Empleado> empleados = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;

        do {
            System.out.println("\n--- MENÚ DE EMPLEADOS ---");
            System.out.println("1. Registrar empleado tiempo completo");
            System.out.println("2. Registrar empleado medio tiempo");
            System.out.println("3. Mostrar empleados registrados");
            System.out.println("4. Mostrar Estadísticas.");
            System.out.println("5. Salir");
            System.out.print("Ingrese opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Opción no válida.");
                continue;
            }

            if (opcion == 1) {
                registrarTiempoCompleto();
            } else if (opcion == 2) {
                registrarMedioTiempo();
            } else if (opcion == 3) {
                mostrarEmpleados();
            } else if (opcion == 4) {
                mostrarEstadisticas();
            } else if (opcion == 5) {
                System.out.println("Saliendo del sistema TechESFOT...");
            } else {
                System.out.println("Error: Opción no válida.");
            }
        } while (opcion != 5);
    }

    private static boolean existeCodigo(String codigo) {
        for (Empleado e : empleados) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }

    private static String[] separarNombreApellido(String entrada) {
        String[] partes = entrada.trim().split("\\s+", 2);
        String nombre = partes[0];
        String apellido = partes.length > 1 ? partes[1] : "";
        return new String[]{nombre, apellido};
    }

    private static void registrarTiempoCompleto() {
        System.out.print("Código: ");
        String codigo = sc.nextLine().trim();

        if (codigo.isEmpty()) {
            System.out.println("Error: El código no puede estar vacío.");
            return;
        }

        if (existeCodigo(codigo)) {
            System.out.println("Error: El código " + codigo + " ya está registrado.");
            return;
        }

        try {
            System.out.print("Nombre: ");
            String entradaNombre = sc.nextLine().trim();
            if (entradaNombre.isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacío.");
                return;
            }
            String[] nomApe = separarNombreApellido(entradaNombre);

            System.out.print("Cargo: ");
            String cargo = sc.nextLine().trim();

            System.out.print("Sueldo base: ");
            double sueldoBase = Double.parseDouble(sc.nextLine().trim());
            if (sueldoBase < 0) {
                System.out.println("Error: El sueldo base no puede ser negativo.");
                return;
            }

            System.out.print("Bono: ");
            double bono = Double.parseDouble(sc.nextLine().trim());
            if (bono < 0) {
                System.out.println("Error: El bono no puede ser negativo.");
                return;
            }


            Empleado emp = new EmpleadoTiempoCompleto(codigo, nomApe[0], nomApe[1], cargo, sueldoBase, bono);
            empleados.add(emp);
            System.out.println("Empleado tiempo completo registrado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numérico válido.");
        }
    }

    private static void registrarMedioTiempo() {
        System.out.print("Código: ");
        String codigo = sc.nextLine().trim();

        if (codigo.isEmpty()) {
            System.out.println("Error: El código no puede estar vacío.");
            return;
        }

        if (existeCodigo(codigo)) {
            System.out.println("Error: El código " + codigo + " ya está registrado.");
            return;
        }

        try {
            System.out.print("Nombre: ");
            String entradaNombre = sc.nextLine().trim();
            if (entradaNombre.isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacío.");
                return;
            }
            String[] nomApe = separarNombreApellido(entradaNombre);

            System.out.print("Cargo: ");
            String cargo = sc.nextLine().trim();

            System.out.print("Horas trabajadas: ");
            int horas = Integer.parseInt(sc.nextLine().trim());
            if (horas < 0) {
                System.out.println("Error: Las horas trabajadas no pueden ser negativas.");
                return;
            }

            System.out.print("Pago por hora: ");
            double pagoHora = Double.parseDouble(sc.nextLine().trim());
            if (pagoHora < 0) {
                System.out.println("Error: El pago por hora no puede ser negativo.");
                return;
            }

            // Polimorfismo al crear el objeto con referencia de la superclase Empleado
            Empleado emp = new EmpleadoMedioTiempo(codigo, nomApe[0], nomApe[1], cargo, horas, pagoHora);
            empleados.add(emp);
            System.out.println("Empleado medio tiempo registrado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un valor numérico válido.");
        }
    }

    private static void mostrarEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("Error: La lista de empleados está vacía.");
            return;
        }

        System.out.println("--- LISTA DE EMPLEADOS REGISTRADOS ---");
        for (Empleado e : empleados) {
            System.out.println(e);
        }
    }

    private static void mostrarEstadisticas() {
        if (empleados.isEmpty()) {
            System.out.println("Error: La lista de empleados está vacía.");
            return;
        }

        Empleado mayorSueldo = empleados.get(0);
        double sumaSueldos = 0.0;

        for (Empleado e : empleados) {
            double sueldoActual = e.calcularSueldo();
            sumaSueldos += sueldoActual;
            if (sueldoActual > mayorSueldo.calcularSueldo()) {
                mayorSueldo = e;
            }
        }

        double promedio = sumaSueldos / empleados.size();
        System.out.println("El empleado con mayor sueldo es: " + mayorSueldo.getNombreCompleto());
        System.out.printf("El promedio salarial entre todos los empleados es:  $%.2f%n", promedio);
    }
}