<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Autor</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous" />
    </head>
    <body>
        <div class="container">
            <h1>Autor</h1>
            <div>
                Nome: ${autor.nome}
            </div>
            <div>
                Livros:
                <ul>
                    <c:forEach var="l" items="${autor.livros}">
                        <li>${l.titulo}</li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </body>
</html>