var urlBaseProducto = "/api/productos";
var urlBaseCategoria = "/api/categorias";

var consultarCategorias = function (idcategoria) {
    $.ajax({
        url: urlBaseCategoria + "/all",
        type: 'GET',
        dataType: 'json',
        success: function (respuesta) {
            var select = `<select class="form-select" id="categoria">`;
            for (var i = 0; i < respuesta.length; i++) {
                select += `<option value="${respuesta[i].codigo}">${respuesta[i].nombre}</option>`;
            }
            select += `</select>`;
            $("#categoria-select").html(select);
            
            if (idcategoria!=='undefined' && idcategoria!=null){
                $("#categoria").val(idcategoria);
            }
            
        },
        error: function (xhr, status) {
            console.log(xhr);
            console.log(status);
            alert('ha sucedido un problema');
        }
    });
}

var consultar = function () {
    $.ajax({
        url: urlBaseProducto + "/all",
        type: 'GET',
        dataType: 'json',
        success: function (respuesta) {
            console.log(respuesta);
            actualizarTabla(respuesta);
        },
        error: function (xhr, status) {
            console.log(xhr);
            console.log(status);
            alert('ha sucedido un problema');
        }
    });
}

var actualizarTabla = function (items) {
    var tabla = `<table class="table striped">
                  <tr>
                    <th>COD</th>
                    <th>NOMBRE</th>
                    <th>PRECIO</th>
                    <th>INVENTARIO</th>
                    <th>CATEGORIA</th>
                    <th>ACCIONES</th>
                  </tr>`;


    for (var i = 0; i < items.length; i++) {
        tabla += `<tr>
                   <td>${items[i].codigo}</td>
                   <td>${items[i].nombre}</td>
                   <td>${items[i].precio}</td>
                   <td>${items[i].inventario}</td>
                   <td>${items[i].categoria.nombre}</td>
                   <td style="margin:0">
                    <button type="button" class="btn btn-sm btn-primary" onclick="editar(${items[i].codigo}, '${items[i].nombre}', '${items[i].precio}', '${items[i].inventario}', '${items[i].categoria.codigo}')">
                        Editar
                    </button>
                    <button type="button" class="btn btn-sm btn-danger" onclick="eliminar(${items[i].codigo})">
                        Eliminar
                    </button>
                   </td>
                </tr>`;
    }
    tabla += `</table>`;

    $("#tabla").html(tabla);
}


$(document).ready(function () {
    console.log("document ready")
    consultar();
});


var nuevo = function () {
    consultarCategorias(null);
    $("#tituloModalProducto").html('Nuevo Producto');
    $("#id").val('');
    $("#nombre").val('');
    $("#precio").val('');
    $("#inventario").val('');
    $('#modalProducto').modal('show');
}

var cerrarModal = function () {
    $('#modalProducto').modal('hide');
}

var guardarCambios = function () {
    var payload;
    var method;
    var id = $("#id").val();
    var msg;
    var ruta;
    if (id !== 'undefined' && id != null && id.length > 0) {
        ruta = urlBaseProducto + "/update";
        payload = {
            codigo: +$("#id").val(),
            nombre: $("#nombre").val(),
            precio: +$("#precio").val(),
            inventario: +$("#inventario").val(),
            categoria: {
                codigo: +$("#categoria").val()
            }
        };
        method = "PUT";
        msg = "se ha actualizado el producto";
    } else {
        ruta = urlBaseProducto + "/save";
        payload = {
            nombre: $("#nombre").val(),
            precio: +$("#precio").val(),
            inventario: +$("#inventario").val(),
            categoria: {
                codigo: +$("#categoria").val()
            }
        };
        method = "POST";
        msg = "se ha creado el producto";
    }

    console.log("guardando ", payload)
    console.log("metodo ", method, "a", ruta)

    $.ajax({
        url: ruta,
        type: method,
        dataType: 'json',
        headers: {
            "Content-Type": "application/json"
        },
        data: JSON.stringify(payload),
        statusCode: {
            201: function () {
                mostrarMensaje(msg);
                cerrarModal();
                consultar();
            }
        },
    });
}

var editar = function (id, nombre, precio, inventario, idcategoria) {
    console.log(nombre, precio, inventario, idcategoria);
    consultarCategorias(idcategoria);
    $("#tituloModalProducto").html('Actualizar Producto');
    $("#id").val(id);
    $("#nombre").val(nombre);
    $("#precio").val(precio);
    $("#inventario").val(inventario);
    
    $('#modalProducto').modal('show');
}

var eliminar = function (id) {
    console.log("eliminando id: " + id)
    $.ajax({
        url: urlBaseProducto + "/" + id,
        type: 'DELETE',
        dataType: 'json',
        headers: {
            "Content-Type": "application/json"
        }, 
        statusCode: {
            204: function () {
                mostrarMensaje('Se ha eliminado el producto');
                cerrarModal();
                consultar();
            }
        },
    });
}

var mostrarMensaje = function (mensaje) {
    $("#mensaje").html(mensaje);
    $('#modalMensaje').modal('show');
}

var cerrarModalMensaje = function () {
    $('#modalMensaje').modal('hide');
}