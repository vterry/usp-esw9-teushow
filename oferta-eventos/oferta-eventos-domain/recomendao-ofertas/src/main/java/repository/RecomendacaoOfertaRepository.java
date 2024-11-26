package repository;

import entity.RecomendacaoDeOfertas;
import valueobjects.IdCliente;

import java.util.List;
import java.util.Optional;

public interface RecomendacaoOfertaRepository {

    RecomendacaoDeOfertas save(RecomendacaoDeOfertas recomendacaoDeOfertas);

    List<RecomendacaoDeOfertas> listarRecomendacaoParaCliente (IdCliente idCliente);

}
