package ro.ase.biblioteca.entities;


import ro.ase.biblioteca.contracts.IPermisBiblioteca;

import java.time.LocalDate;

public class PermisBiblioteca extends Entitate implements IPermisBiblioteca {
    private LocalDate dataEliberarii;
    private LocalDate dataExpirarii;

    public PermisBiblioteca(LocalDate dataEliberarii, LocalDate dataExpirarii) {
        super();
        this.dataEliberarii = dataEliberarii;
        this.dataExpirarii = dataExpirarii;
    }

    public LocalDate getDataEliberarii() {
        return dataEliberarii;
    }

    public void setDataEliberarii(LocalDate dataEliberarii) {
        this.dataEliberarii = dataEliberarii;
    }

    public LocalDate getDataExpirarii() {
        return dataExpirarii;
    }

    public void setDataExpirarii(LocalDate dataExpirarii) {
        this.dataExpirarii = dataExpirarii;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PERMIS BIBLIOTECA[");
        sb.append("id=").append(super.getId());
        sb.append(", dataEliberarii=").append(dataEliberarii);
        sb.append(", dataExpirarii=").append(dataExpirarii);
        sb.append(']');
        return sb.toString();
    }

    @Override
    public boolean permisBibliotecaValid() {
        return LocalDate.now().compareTo(dataEliberarii) >= 0 && LocalDate.now().compareTo(dataExpirarii) <= 0;
    }
}
