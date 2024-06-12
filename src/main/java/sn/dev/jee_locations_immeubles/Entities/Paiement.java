package sn.dev.jee_locations_immeubles.Entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Paiement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "MONTANT")
    private BigDecimal montant;
    @Basic
    @Column(name = "DATE_PAIEMENT")
    private Date datePaiement;
    @Basic
    @Column(name = "CONTRAT_LOCATION_ID")
    private Integer contratLocationId;
    @ManyToOne
    @JoinColumn(name = "CONTRAT_LOCATION_ID", referencedColumnName = "ID")
    private Contratlocation contratlocationByContratLocationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public Integer getContratLocationId() {
        return contratLocationId;
    }

    public void setContratLocationId(Integer contratLocationId) {
        this.contratLocationId = contratLocationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paiement paiement = (Paiement) o;

        if (id != paiement.id) return false;
        if (montant != null ? !montant.equals(paiement.montant) : paiement.montant != null) return false;
        if (datePaiement != null ? !datePaiement.equals(paiement.datePaiement) : paiement.datePaiement != null)
            return false;
        if (contratLocationId != null ? !contratLocationId.equals(paiement.contratLocationId) : paiement.contratLocationId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (montant != null ? montant.hashCode() : 0);
        result = 31 * result + (datePaiement != null ? datePaiement.hashCode() : 0);
        result = 31 * result + (contratLocationId != null ? contratLocationId.hashCode() : 0);
        return result;
    }

    public Contratlocation getContratlocationByContratLocationId() {
        return contratlocationByContratLocationId;
    }

    public void setContratlocationByContratLocationId(Contratlocation contratlocationByContratLocationId) {
        this.contratlocationByContratLocationId = contratlocationByContratLocationId;
    }
}
