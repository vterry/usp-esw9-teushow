package valueobjects;

import java.util.UUID;

public class IdPerfil extends BaseId<UUID> {
    protected IdPerfil(UUID value) {
        super(value);
    }
}
