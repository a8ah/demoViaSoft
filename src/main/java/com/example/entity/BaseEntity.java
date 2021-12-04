package com.example.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.example.util.DateFormatterUtil;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public abstract class BaseEntity implements Entidad {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected LocalDateTime creado;

    @LastModifiedDate
    @Column
    private LocalDateTime modificado;

    @Column
    protected boolean eliminado;

    @Override
    public LocalDateTime getCreado() {
        return creado;
    }

    @Override
    public void setCreado(LocalDateTime creado) {
        this.creado = creado;
    }

    @Override
    public LocalDateTime getModificado() {
        return modificado;
    }

    @Override
    public void setModificado(LocalDateTime modificado) {
        this.modificado = modificado;
    }

    @Override
    public boolean isEliminado() {
        return eliminado;
    }

    @Override
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public String getCreadoFormatted() {
        return DateFormatterUtil.format(this.getCreado());
    }

    public String getModificadoFormatted() {
        if (this.getModificado() == null) {
            return null;
        }

        return DateFormatterUtil.format(this.getModificado());
    }

}
