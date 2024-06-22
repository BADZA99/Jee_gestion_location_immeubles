package sn.dev.jee_locations_immeubles.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Contratlocation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "DATE_DEBUT")
    private Date dateDebut;
    @Basic
    @Column(name = "DATE_FIN")
    private Date dateFin;
    @ManyToOne
    @JoinColumn(name = "UNITE_LOCATION_ID", referencedColumnName = "ID")
    private Unitelocation unitelocationByUniteLocationId;
    @ManyToOne
    @JoinColumn(name = "LOCATAIRE_ID", referencedColumnName = "ID")
    private Locataire locataireByLocataireId;
    @OneToMany(mappedBy = "contratlocationByContratLocationId")
    private Collection<Paiement> paiementsById;






    @Basic
    @Column(name = "Statut")
    private String statut;

    public Contratlocation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Unitelocation getUnitelocationByUniteLocationId() {
        return unitelocationByUniteLocationId;
    }

    public void setUnitelocationByUniteLocationId(Unitelocation unitelocationByUniteLocationId) {
        this.unitelocationByUniteLocationId = unitelocationByUniteLocationId;
    }

    public Locataire getLocataireByLocataireId() {
        return locataireByLocataireId;
    }

    public void setLocataireByLocataireId(Locataire locataireByLocataireId) {
        this.locataireByLocataireId = locataireByLocataireId;
    }

    public Collection<Paiement> getPaiementsById() {
        return paiementsById;
    }

    public void setPaiementsById(Collection<Paiement> paiementsById) {
        this.paiementsById = paiementsById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contratlocation that = (Contratlocation) o;
        return id == that.id &&
                Objects.equals(dateDebut, that.dateDebut) &&
                Objects.equals(dateFin, that.dateFin) &&
                Objects.equals(unitelocationByUniteLocationId, that.unitelocationByUniteLocationId) &&
                Objects.equals(locataireByLocataireId, that.locataireByLocataireId) &&
                Objects.equals(paiementsById, that.paiementsById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateDebut, dateFin, unitelocationByUniteLocationId, locataireByLocataireId, paiementsById);
    }

    //public Integer getUniteLocationId() {
    //return uniteLocationId;
    //}

    //public void setUniteLocationId(Integer uniteLocationId) {
    //this.uniteLocationId = uniteLocationId;
    //}

    // public Integer getLocataireId() {
    //     return locataireId;
    // }

    // public void setLocataireId(Integer locataireId) {
    //     this.locataireId = locataireId;
    // }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Contratlocation{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", unitelocationByUniteLocationId=" + (unitelocationByUniteLocationId != null ? unitelocationByUniteLocationId.getId() : "null") +
                ", locataireByLocataireId=" + (locataireByLocataireId != null ? locataireByLocataireId.getId() : "null") +
                ", statut='" + statut + '\'' +
                '}';
    }

}