package sn.dev.jee_locations_immeubles.Entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

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
    @Basic
    @Column(name = "IMAGE")
    private String image;
    @OneToMany(mappedBy = "immeubleByImmeubleId")
    private Collection<Unitelocation> unitelocationsById;

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
        if (!Objects.equals(nom, immeuble.nom)) return false;
        if (!Objects.equals(adresse, immeuble.adresse)) return false;
        if (!Objects.equals(description, immeuble.description))
            return false;
        if (!Objects.equals(equipements, immeuble.equipements))
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<Unitelocation> getUnitelocationsById() {
        return unitelocationsById;
    }

    public void setUnitelocationsById(Collection<Unitelocation> unitelocationsById) {
        this.unitelocationsById = unitelocationsById;
    }

    @Override
    public String toString() {
        return "Immeuble [id=" + id + ", nom=" + nom + ", description=" + description + ", adresse=" + adresse + ", equipements=" + equipements + ", image=" + image + "]";
    }
}
