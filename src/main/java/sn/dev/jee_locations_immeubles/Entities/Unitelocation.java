package sn.dev.jee_locations_immeubles.Entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

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
    @Basic
    @Column(name = "IMMEUBLE_ID")
    private Integer immeubleId;
    @OneToMany(mappedBy = "unitelocationByUniteLocationId")
    private Collection<Contratlocation> contratlocationsById;
    @ManyToOne
    @JoinColumn(name = "IMMEUBLE_ID", referencedColumnName = "ID")
    private Immeuble immeubleByImmeubleId;

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

    public Integer getImmeubleId() {
        return immeubleId;
    }

    public void setImmeubleId(Integer immeubleId) {
        this.immeubleId = immeubleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Unitelocation that = (Unitelocation) o;

        if (id != that.id) return false;
        if (numeroUnite != null ? !numeroUnite.equals(that.numeroUnite) : that.numeroUnite != null) return false;
        if (nombrePieces != null ? !nombrePieces.equals(that.nombrePieces) : that.nombrePieces != null) return false;
        if (superficie != null ? !superficie.equals(that.superficie) : that.superficie != null) return false;
        if (loyer != null ? !loyer.equals(that.loyer) : that.loyer != null) return false;
        if (immeubleId != null ? !immeubleId.equals(that.immeubleId) : that.immeubleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (numeroUnite != null ? numeroUnite.hashCode() : 0);
        result = 31 * result + (nombrePieces != null ? nombrePieces.hashCode() : 0);
        result = 31 * result + (superficie != null ? superficie.hashCode() : 0);
        result = 31 * result + (loyer != null ? loyer.hashCode() : 0);
        result = 31 * result + (immeubleId != null ? immeubleId.hashCode() : 0);
        return result;
    }

    public Collection<Contratlocation> getContratlocationsById() {
        return contratlocationsById;
    }

    public void setContratlocationsById(Collection<Contratlocation> contratlocationsById) {
        this.contratlocationsById = contratlocationsById;
    }

    public Immeuble getImmeubleByImmeubleId() {
        return immeubleByImmeubleId;
    }

    public void setImmeubleByImmeubleId(Immeuble immeubleByImmeubleId) {
        this.immeubleByImmeubleId = immeubleByImmeubleId;
    }
}
