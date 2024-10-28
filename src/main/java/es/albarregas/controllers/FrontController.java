/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.models.Sumador;
import es.albarregas.models.Divisor;
import es.albarregas.models.Multiplicador;
import es.albarregas.models.Restador;
import es.albarregas.beans.Calculator;
import es.albarregas.exceptions.DivisionPorCeroException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jesus
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.setAttribute("error", "Se está intentando acceder sin pasar por el formulario de entrada");
        request.getRequestDispatcher(".").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Date fecha;
        SimpleDateFormat sdf;
        boolean error;
        String url = "JSP/resultado.jsp";
        Calculator calculator = new Calculator();

        try {
            // Capturamos los datos enviados desde el formulario y los validamos
            int operando1 = Integer.parseInt(request.getParameter("operando1"));
            int operando2 = Integer.parseInt(request.getParameter("operando2"));
            String operacion = request.getParameter("operacion");

            error = false;
            // Dependiendo de la operación a realizar llamamos a un modelo u a otro
            // En cualquiere caso le pasaremos los dos operandos y recibiremos el resultado
            switch (operacion) {
                case "suma": // Accedemos al modelo Sumador
                    Sumador sumador = new Sumador();

                    calculator.setResultado(sumador.sumar(operando1, operando2));
                    calculator.setSigno("+");
                    break;
                case "resta": // Accedemos al modelo Restador
                    Restador restador = new Restador();

                    calculator.setResultado(restador.restar(operando1, operando2));
                    calculator.setSigno("-");
                    break;
                case "producto": // Accedemos al modelo Multiplicador
                    Multiplicador multiplicador = new Multiplicador();

                    calculator.setResultado(multiplicador.multiplicar(operando1, operando2));
                    calculator.setSigno("*");
                    break;
                case "division": // Accedemos al modelo Divisor
                    Divisor divisor = new Divisor();
                    try {
                        // LLamamos al método dividir del modelo pasándole los dos operandos
                        calculator.setResultado(divisor.dividir(operando1, operando2));
                        calculator.setSigno("/");
                    } catch (DivisionPorCeroException e) {
                        // En el caso de producirse el error de intentar dividir por cero se captura la excepción
                        error = true;

                    }
            }
            if (error) {
                // En el caso de que se esté intentando dividir por cero creamos un atributo de petición para informar al usuario
                request.setAttribute("error", "Se está intentando dividir por cero");
                // Redirigimos la salida para que muestre el error
                url = "JSP/error.jsp";
            } else {
                // Cuando todo está correcto, preparamos los datos de la salida
                fecha = new Date();
                sdf = new SimpleDateFormat("dd-MM-yyyy  HH:mm:ss");

                calculator.setOperando1(operando1);
                calculator.setOperando2(operando2);
                // Creamos dos atributos de petición para poder mostrarlos en la salida
                request.setAttribute("fecha", sdf.format(fecha));
                request.setAttribute("calcula", calculator);


            }

        } catch (NumberFormatException e) {
            // En el caso de que los datos de entrada no sean válidos para el proceso, creamos un atributo de petición
            // para avisar al usuario
            request.setAttribute("error", "Alguno de los operandos no es un número válido");
            // Redirigimos la salida para que visualice el error
            url = "JSP/error.jsp";
        }
        // Accedemos al siguiente componente que puede ser la salida normal o el aviso de error
        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
