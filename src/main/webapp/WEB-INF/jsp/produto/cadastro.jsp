<%-- 
    Document   : cadastro
    Created on : 12/07/2016, 13:36:14
    Author     : Sensum
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de produto</title>
        <script type="text/JavaScript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
    </head>
    <body>
        <select id="people"></select>
    </body>
    
    <script type="text/JavaScript">
        //get a reference to the select element
        var $select = $('#people');
 
        //request the JSON data and parse into the select element
        $.getJSON('../marca', function(data){
 
            //clear the current content of the select
            $select.html('');

            //iterate over the data and append a select option
            $.each(data.marcas, function(key, val){ 
                $select.append('<option id="' + val.codigo + '">' + val.nome + '</option>');
            })
        });
  </script>
</html>
