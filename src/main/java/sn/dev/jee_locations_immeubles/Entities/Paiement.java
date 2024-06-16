package sn.dev.jee_locations_immeubles.Entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

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

    public Contratlocation getContratlocationByContratLocationId() {
        return contratlocationByContratLocationId;
    }

    public void setContratlocationByContratLocationId(Contratlocation contratlocationByContratLocationId) {
        this.contratlocationByContratLocationId = contratlocationByContratLocationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Paiement paiement = (Paiement) o;
        return id == paiement.id &&
                Objects.equals(montant, paiement.montant) &&
                Objects.equals(datePaiement, paiement.datePaiement) &&
                Objects.equals(contratlocationByContratLocationId, paiement.contratlocationByContratLocationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, montant, datePaiement, contratlocationByContratLocationId);
    }
}
