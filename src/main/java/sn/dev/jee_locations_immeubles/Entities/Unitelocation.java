package sn.dev.jee_locations_immeubles.Entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Unitelocation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "NUMERO_UNITE")
    private String numeroUnite;
    @Basic
    @Column(name = "NOMBRE_PIECES")
    private Integer nombrePieces;
    @Basic
    @Column(name = "SUPERFICIE")
    private BigDecimal superficie;
    @Basic
    @Column(name = "LOYER")
    private BigDecimal loyer;
    @OneToMany(mappedBy = "unitelocationByUniteLocationId")
    private Collection<Contratlocation> contratlocationsById;
    @ManyToOne
    @JoinColumn(name = "IMMEUBLE_ID", referencedColumnName = "ID")
    private Immeuble immeubleByImmeubleId;
    @Basic
    @Column(name = "IMAGE")
    private String image;
    @Basic
    @Column(name = "TARIF_LOCATION")
    private BigDecimal tarifLocation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroUnite() {
        return numeroUnite;
    }

    public void setNumeroUnite(String numeroUnite) {
        this.numeroUnite = numeroUnite;
    }

    public Integer getNombrePieces() {
        return nombrePieces;
    }

    public void setNombrePieces(Integer nombrePieces) {
        this.nombrePieces = nombrePieces;
    }

    public BigDecimal getSuperficie() {
        return superficie;
    }

    public void setSuperficie(BigDecimal superficie) {
        this.superficie = superficie;
    }

    public BigDecimal getLoyer() {
        return loyer;
    }

    public void setLoyer(BigDecimal loyer) {
        this.loyer = loyer;
    }

    public Immeuble getImmeubleByImmeubleId() {
        return immeubleByImmeubleId;
    }

    public void setImmeubleByImmeubleId(Immeuble immeubleByImmeubleId) {
        this.immeubleByImmeubleId = immeubleByImmeubleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Unitelocation that = (Unitelocation) o;

        if (id != that.id)
            return false;
        if (!Objects.equals(numeroUnite, that.numeroUnite))
            return false;
        if (!Objects.equals(nombrePieces, that.nombrePieces))
            return false;
        if (!Objects.equals(superficie, that.superficie))
            return false;
        if (!Objects.equals(loyer, that.loyer))
            return false;
        if (!Objects.equals(immeubleByImmeubleId, that.immeubleByImmeubleId))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (numeroUnite != null ? numeroUnite.hashCode() : 0);
        result = 31 * result + (nombrePieces != null ? nombrePieces.hashCode() : 0);
        result = 31 * result + (superficie != null ? superficie.hashCode() : 0);
        result = 31 * result + (loyer != null ? loyer.hashCode() : 0);
        result = 31 * result + (immeubleByImmeubleId != null ? immeubleByImmeubleId.hashCode() : 0);
        return result;
    }

    public Collection<Contratlocation> getContratlocationsById() {
        return contratlocationsById;
    }

    public void setContratlocationsById(Collection<Contratlocation> contratlocationsById) {
        this.contratlocationsById = contratlocationsById;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getTarifLocation() {
        return tarifLocation;
    }

    public void setTarifLocation(BigDecimal tarifLocation) {
        this.tarifLocation = tarifLocation;
    }

     @Override
    public String toString() {
        return "uniteloc [id=" + id + ", numeroUnite=" + numeroUnite + ", nombrePieces=" + nombrePieces + ", superficie=" + superficie + ", loyer=" + loyer + ", image=" + image + "]";
    }
}
