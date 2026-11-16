package org.example;

public class EmpleadoMedioTiempo extends Empleado {
    private int horasTrabajadas;
    private double pagoPorHora;

    public EmpleadoMedioTiempo(String codigo, String nombre, String apellido, String cargo,
                               int horasTrabajadas, double pagoPorHora) {
        super(codigo, nombre, apellido, cargo, 0.0);
        this.horasTrabajadas = horasTrabajadas;
        this.pagoPorHora = pagoPorHora;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public double getPagoPorHora() {
        return pagoPorHora;
    }

    @Override
    public double calcularSueldo() {
        return getSueldoBase() + (horasTrabajadas * pagoPorHora);
    }
}