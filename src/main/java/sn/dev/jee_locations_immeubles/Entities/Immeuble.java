package sn.dev.jee_locations_immeubles.Entities;

import javax.persistence.*;

@Entity
public class Immeuble {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "NOM")
    private String nom;
    @Basic
    @Column(name = "ADRESSE")
    private String adresse;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "EQUIPEMENTS")
    private String equipements;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEquipements() {
        return equipements;
    }

    public void setEquipements(String equipements) {
        this.equipements = equipements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Immeuble immeuble = (Immeuble) o;

        if (id != immeuble.id) return false;
        if (nom != null ? !nom.equals(immeuble.nom) : immeuble.nom != null) return false;
        if (adresse != null ? !adresse.equals(immeuble.adresse) : immeuble.adresse != null) return false;
        if (description != null ? !description.equals(immeuble.description) : immeuble.description != null)
            return false;
        if (equipements != null ? !equipements.equals(immeuble.equipements) : immeuble.equipements != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (equipements != null ? equipements.hashCode() : 0);
        return result;
    }
}
