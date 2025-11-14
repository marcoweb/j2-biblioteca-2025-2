<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Livros</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous" />
    </head>
    <body>
        <div class="container">
            <h1>Livros</h1>
            <a href="/livros/insert" class="btn btn-primary">Novo Livro</a>
            <table class="table">
                <thead>
                    <tr>
                        <td>ID</td> 
                        <td>TITULO</td>
                        <td>GÊNERO</td>
                        <td>AUTOR(ES)</td>
                        <td>&nbsp;</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="l" items="${livros}">
                        <tr>
                            <td>${l.id}</td>
                            <td>${l.titulo}</td>
                            <td>${l.genero.nome}</td>
                            <td>
                                <c:forEach var="a" items="${l.autores}">
                                    ${a.nome} 
                                </c:forEach>
                            </td>
                            
                            <td>
                                <a href="/livros/update/${l.id}" class="btn btn-warning">Editar</a>
                                <a href="/livros/delete/${l.id}" class="btn btn-danger">Remover</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>