package com.example.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface Entidad {
  UUID getId();

  void setId(UUID id);

  boolean isEliminado();

  void setEliminado(boolean eliminado);

  LocalDateTime getCreado();

  void setCreado(LocalDateTime creado);

  LocalDateTime getModificado();

  void setModificado(LocalDateTime modificado);

}
