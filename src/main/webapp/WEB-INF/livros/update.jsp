<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Editar Livro</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous" />
    </head>
    <body>
        <div class="container">
            <h1>Editar Livro</h1>
            <form action="/livros/update" method="post">
                <input type="hidden" name="id" value="${livro.id}" />
                <div>
                    <label class="form-label" for="titulo">Título</label>
                    <input class="form-control" type="text" name="titulo" value="${livro.titulo}" />
                </div>
                <div>
                    <label class="form-label" for="genero">Gênero</label>
                    <select class="form-select" name="genero_id">
                        <c:forEach var="g" items="${generos}">
                            <option ${livro.genero.id == g.id ? "selected" : ""} value="${g.id}">${g.nome}</option>
                        </c:forEach>
                    </select>
                </div>
                <br />
                <button type="submit" class="btn btn-success">Salvar</button>
            </form>
        </div>
    </body>
</html>