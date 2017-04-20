<%-- 
    Document   : formulario
    Created on : 21/03/2017, 10:21:49
    Author     : Fábio
--%>

<h1>Cadastro de Usuários</h1>

<form class="form-horizontal" action="${linkTo[UsuarioController].cadastrar}" method="POST" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="usuario.codigo" value="${usuario.codigo}">

    <div class=row">
        <mt:campoTexto type="text" id="nome" label="Nome:" name="usuario.nome" value="${usuario.nome}" placeholder="Informe seu nome" autofocus="autofocus" />
        
        <mt:campoTexto type="email" id="email" label="E-mail" name="usuario.email" value="${usuario.email}" placeholder="Informe seu e-mail" />
        
        <mt:campoTexto type="password" id="senha" label="Senha" name="usuario.senha" value="${usuario.senha}" placeholder="Informe uma senha" />
    </div>
    
    <div class=row">
        <div class="form-group col-xs-12">
            <button type="submit" class="btn btn-default center-block">Cadastrar</button>
        </div>
    </div>
    
</form>