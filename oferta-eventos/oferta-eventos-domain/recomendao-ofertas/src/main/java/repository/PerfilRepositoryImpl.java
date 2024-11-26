package repository;

import entity.Perfil;
import valueobjects.IdPerfil;
import valueobjects.TipoPerfil;

import java.util.List;
import java.util.Optional;

public class PerfilRepositoryImpl implements PerfilRepository{

    //TODO implementar busca de perfis
    @Override
    public Perfil save(Perfil perfil) {
        return null;
    }

    //TODO implementar busca de perfis por id
    @Override
    public Optional<Perfil> buscarPerfilPorId(IdPerfil idPerfil, TipoPerfil tipoPerfil) {
        return Optional.empty();
    }

    //TODO implementar busca perfis por tipo
    @Override
    public List<Perfil> buscarPerfilPorTipo(TipoPerfil tipoPerfil) {
        return List.of();
    }

    // TODO Implementar builder para construção do cliente - Copy Entity
    private void clienteBuilder(){
    }
}
