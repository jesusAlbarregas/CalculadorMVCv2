<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />

<!DOCTYPE html>
<html lang="es">
    <head>
        <%-- Incluimos la metainformación de la cabecera --%>
        <jsp:include page="/INC/metas.inc"/>
        <title>Calculadora</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/estilo.css" media="screen" />
    </head>
    <body>
        <%-- Incluimos la cabecera de la aplicación que son las dos imágenes --%>
        <jsp:include page="/INC/cabecera.inc"/>
        
        <h2 id="error">AVISO !! ${requestScope.error}</h2>
        <%-- Acceso al inicio de la aplicación --%>
        <p><a href="${pageContext.request.contextPath}/FrontController">Calculadora</a></p>
        <%-- Incluimos el pie de la aplicación que el copyright --%>
        <jsp:include page="/INC/pie.inc" />
    </body>
</html>