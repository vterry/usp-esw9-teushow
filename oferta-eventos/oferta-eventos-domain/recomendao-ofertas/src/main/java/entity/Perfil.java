package entity;

import valueobjects.IdCliente;
import valueobjects.IdPerfil;
import valueobjects.TipoPerfil;

// TODO - Validar se essa entidade pode ter seu próprio repositorio
// TODO - Validar se essa entidade pode retornar uma cópia do "Cliente" somado a seu perfil neste contexto

public class Perfil extends BaseEntity<IdPerfil> {
    private IdCliente idCliente;
    private TipoPerfil tipoPerfil;

    Perfil(IdPerfil idPerfil, IdCliente idCliente, TipoPerfil tipoPerfil){
        super.setId(idPerfil);
        this.idCliente = idCliente;
        this.tipoPerfil = tipoPerfil;
    }
}
