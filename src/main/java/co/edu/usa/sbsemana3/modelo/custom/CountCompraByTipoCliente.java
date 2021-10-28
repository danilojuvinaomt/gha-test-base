/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.usa.sbsemana3.modelo.custom;

/**
 *
 * @author juvinao
 */
public class CountCompraByTipoCliente {

    private String tipoCliente;
    private Long cantidad;

    public CountCompraByTipoCliente(String tipoCliente, Long cantidad) {
        this.tipoCliente = tipoCliente;
        this.cantidad = cantidad;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

}
