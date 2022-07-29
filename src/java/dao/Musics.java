/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stefanini
 */
@Entity
@Table(name = "Musics")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Musics.findAll", query = "SELECT u FROM Musics u"),
    @NamedQuery(name = "Musics.findById", query = "SELECT u FROM Musics u WHERE u.id = :id"),
    @NamedQuery(name = "Musics.findByName", query = "SELECT u FROM Musics u WHERE u.name = :name"),
    @NamedQuery(name = "Musics.findByArtist", query = "SELECT u FROM Musics u WHERE u.artist = :artist")})
public class Musics implements Serializable {

    private static final long serialVersionUID = 1L;
    /*@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "description")
    private String description;*/
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "artist")
    private String artist;

    public Musics() {
        System.out.println("Musics class");
    }

    public Musics(Long id) {
        this.id = id;
    }

    public Musics(Long id, String name, String artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
    }

    /*public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }*/

    public Long getId() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++: "+id);
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Musics)) {
            return false;
        }
        Musics other = (Musics) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dao.Musics[ id=" + id + " ]";
    }
    
}
