package sn.dev.jee_locations_immeubles.Entities;

import javax.persistence.*;
import java.sql.Date;

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
    @Basic
    @Column(name = "UNITE_LOCATION_ID")
    private Integer uniteLocationId;
    @Basic
    @Column(name = "LOCATAIRE_ID")
    private Integer locataireId;

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

    public Integer getUniteLocationId() {
        return uniteLocationId;
    }

    public void setUniteLocationId(Integer uniteLocationId) {
        this.uniteLocationId = uniteLocationId;
    }

    public Integer getLocataireId() {
        return locataireId;
    }

    public void setLocataireId(Integer locataireId) {
        this.locataireId = locataireId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contratlocation that = (Contratlocation) o;

        if (id != that.id) return false;
        if (dateDebut != null ? !dateDebut.equals(that.dateDebut) : that.dateDebut != null) return false;
        if (dateFin != null ? !dateFin.equals(that.dateFin) : that.dateFin != null) return false;
        if (uniteLocationId != null ? !uniteLocationId.equals(that.uniteLocationId) : that.uniteLocationId != null)
            return false;
        if (locataireId != null ? !locataireId.equals(that.locataireId) : that.locataireId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateDebut != null ? dateDebut.hashCode() : 0);
        result = 31 * result + (dateFin != null ? dateFin.hashCode() : 0);
        result = 31 * result + (uniteLocationId != null ? uniteLocationId.hashCode() : 0);
        result = 31 * result + (locataireId != null ? locataireId.hashCode() : 0);
        return result;
    }
}
