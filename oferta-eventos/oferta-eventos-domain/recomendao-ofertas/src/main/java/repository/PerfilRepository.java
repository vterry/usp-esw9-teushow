package repository;

import entity.Perfil;
import valueobjects.IdPerfil;
import valueobjects.TipoPerfil;

import java.util.List;
import java.util.Optional;

public interface PerfilRepository {

    Perfil save(Perfil perfil);

    Optional<Perfil> buscarPerfilPorId(IdPerfil idPerfil, TipoPerfil tipoPerfil);

    List<Perfil> buscarPerfilPorTipo(TipoPerfil tipoPerfil);
}
