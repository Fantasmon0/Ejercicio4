package org.example;

public abstract class Empleado {
    private String codigo;
    private String nombre;
    private String apellido;
    private String cargo;
    private double sueldoBase;

    public Empleado(String codigo, String nombre, String apellido, String cargo, double sueldoBase) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.sueldoBase = sueldoBase;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombreCompleto() {
        if (apellido == null || apellido.isEmpty()) {
            return nombre;
        }
        return nombre + " " + apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public abstract double calcularSueldo();

    @Override
    public String toString() {
        return String.format("Código: %s, Nombre: %s, Cargo: %s, Sueldo: $%.1f",
                codigo, getNombreCompleto(), cargo, calcularSueldo());
    }
}