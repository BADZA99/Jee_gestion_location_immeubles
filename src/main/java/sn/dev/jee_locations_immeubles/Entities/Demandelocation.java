package sn.dev.jee_locations_immeubles.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Demandelocation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "DATE_DEMANDE")
    private Date dateDemande;
    @Basic
    @Column(name = "STATUT")
    private String statut;
    @Basic
    @Column(name = "LOCATAIRE_ID")
    private int locataireId;
    @Basic
    @Column(name = "UNITE_LOCATION_ID")
    private int uniteLocationId;


    public Demandelocation() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getLocataireId() {
        return locataireId;
    }

    public void setLocataireId(int locataireId) {
        this.locataireId = locataireId;
    }

    public int getUniteLocationId() {
        return uniteLocationId;
    }

    public void setUniteLocationId(int uniteLocationId) {
        this.uniteLocationId = uniteLocationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Demandelocation that = (Demandelocation) o;

        if (id != that.id) return false;
        if (locataireId != that.locataireId) return false;
        if (uniteLocationId != that.uniteLocationId) return false;
        if (dateDemande != null ? !dateDemande.equals(that.dateDemande) : that.dateDemande != null) return false;
        if (statut != null ? !statut.equals(that.statut) : that.statut != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateDemande != null ? dateDemande.hashCode() : 0);
        result = 31 * result + (statut != null ? statut.hashCode() : 0);
        result = 31 * result + locataireId;
        result = 31 * result + uniteLocationId;
        return result;
    }

    @Override
    public String toString() {
        return "Demandelocation{" +
                "id=" + id +
                ", dateDemande=" + dateDemande +
                ", statut='" + statut + '\'' +
                ", locataireId=" + locataireId +
                ", uniteLocationId=" + uniteLocationId +
                '}';
    }
}
