<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />

<!DOCTYPE html>
<html lang="es">
    <head>
        <jsp:include page="/INC/metas.inc"/>
        <title>Calculadora</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/estilo.css" media="screen" />

    </head>
    <body>
        <jsp:include page="/INC/cabecera.inc"/>
        
        <div id="datos">

            <p>Operación realizada <strong>${requestScope.fecha}</strong>
        </div>
        <%-- Mostramos el resultado de la operación --%>
        <h2 id="final">${requestScope.calcula.operando1} ${requestScope.calcula.signo} 
            ${requestScope.calcula.operando2} = ${requestScope.calcula.resultado}</h2>
            
        <%-- Accedemos a un controlador para realizar otra operación --%>
        <p><a href="${pageContext.request.contextPath}/FrontController">Calculadora</a></p>

        <jsp:include page="/INC/pie.inc" />
    </body>
</html>