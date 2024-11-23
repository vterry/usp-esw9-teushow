package valueobjects;

import java.util.UUID;

public class IdCliente extends BaseId<UUID> {
    public IdCliente(UUID value) {
        super(value);
    }
}