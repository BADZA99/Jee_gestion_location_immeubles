package sn.dev.jee_locations_immeubles.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Locataire {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "NOM")
    private String nom;
    @Basic
    @Column(name = "EMAIL")
    private String email;
    @Basic
    @Column(name = "MOT_DE_PASSE")
    private String motDePasse;
    @OneToMany(mappedBy = "locataireByLocataireId")
    private Collection<Contratlocation> contratlocationsById;
    @Basic
    @Column(name = "ID_UTILISATEUR")
    private Integer idUtilisateur;


    public Locataire() {

    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Locataire locataire = (Locataire) o;

        if (id != locataire.id) return false;
        if (nom != null ? !nom.equals(locataire.nom) : locataire.nom != null) return false;
        if (email != null ? !email.equals(locataire.email) : locataire.email != null) return false;
        if (motDePasse != null ? !motDePasse.equals(locataire.motDePasse) : locataire.motDePasse != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (motDePasse != null ? motDePasse.hashCode() : 0);
        return result;
    }

    public Collection<Contratlocation> getContratlocationsById() {
        return contratlocationsById;
    }

    public void setContratlocationsById(Collection<Contratlocation> contratlocationsById) {
        this.contratlocationsById = contratlocationsById;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

     public String toString() {
        return "locataire [id=" + id + ", nom=" + nom + ", motDePasse=" + motDePasse + ", idUser=" + idUtilisateur + "]";
    }
}
