/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package com.nexocommerce.servicio_pedidos.dto;

import lombok.Generated;

public class ErrorResponse {
    private String codigo;
    private String mensaje;

    @Generated
    public String getCodigo() {
        return this.codigo;
    }

    @Generated
    public String getMensaje() {
        return this.mensaje;
    }

    @Generated
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Generated
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ErrorResponse)) {
            return false;
        }
        ErrorResponse other = (ErrorResponse)o;
        if (!other.canEqual(this)) {
            return false;
        }
        String this$codigo = this.getCodigo();
        String other$codigo = other.getCodigo();
        if (this$codigo == null ? other$codigo != null : !this$codigo.equals(other$codigo)) {
            return false;
        }
        String this$mensaje = this.getMensaje();
        String other$mensaje = other.getMensaje();
        return !(this$mensaje == null ? other$mensaje != null : !this$mensaje.equals(other$mensaje));
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof ErrorResponse;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $codigo = this.getCodigo();
        result = result * 59 + ($codigo == null ? 43 : $codigo.hashCode());
        String $mensaje = this.getMensaje();
        result = result * 59 + ($mensaje == null ? 43 : $mensaje.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "ErrorResponse(codigo=" + this.getCodigo() + ", mensaje=" + this.getMensaje() + ")";
    }

    @Generated
    public ErrorResponse(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }
}
