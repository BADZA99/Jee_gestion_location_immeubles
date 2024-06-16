package sn.dev.jee_locations_immeubles.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Utilisateur {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "NOM_UTILISATEUR")
    private String nomUtilisateur;
    @Basic
    @Column(name = "MOT_DE_PASSE")
    private String motDePasse;
    @Basic
    @Column(name = "ROLE")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Utilisateur that = (Utilisateur) o;

        if (id != that.id) return false;
        if (!Objects.equals(nomUtilisateur, that.nomUtilisateur))
            return false;
        if (!Objects.equals(motDePasse, that.motDePasse)) return false;
        if (!Objects.equals(role, that.role)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nomUtilisateur != null ? nomUtilisateur.hashCode() : 0);
        result = 31 * result + (motDePasse != null ? motDePasse.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
