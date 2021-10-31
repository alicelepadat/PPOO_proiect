package ro.ase.biblioteca.entities;

public class Editura extends Entitate {
    private String denumire;
    private String adresa;

    public Editura() {
        super();
    }

    public Editura(String denumire, String adresa) {
        super();
        this.denumire = denumire;
        this.adresa = adresa;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EDITURA[");
        sb.append("id=").append(super.getId());
        sb.append(", denumire='").append(denumire).append('\'');
        sb.append(", adresa='").append(adresa).append('\'');
        sb.append(']');
        return sb.toString();
    }
}
