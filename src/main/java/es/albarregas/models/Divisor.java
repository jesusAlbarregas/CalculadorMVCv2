package es.albarregas.models;

import es.albarregas.exceptions.DivisionPorCeroException;

public class Divisor {

    public int dividir(int dividendo, int divisor) throws DivisionPorCeroException {
        int resultado = 0;
        if (divisor != 0) {
            // En el caso de que pueda realizar la división devolvemos el resultado
            resultado = dividendo / divisor;
        } else {
            // Cuando el divisor es cero lanzamos la excepción
            throw new DivisionPorCeroException("Se ha intentado dividir por cero");
        }
        return resultado;
    }

}
