/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shiba
 */
@Entity
@Table(name = "COMENTARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comentario.findAll", query = "SELECT c FROM Comentario c"),
    @NamedQuery(name = "Comentario.findByIdComentario", query = "SELECT c FROM Comentario c WHERE c.idComentario = :idComentario"),
    @NamedQuery(name = "Comentario.findByTexto", query = "SELECT c FROM Comentario c WHERE c.texto = :texto")})
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "secuencia_post")
    @SequenceGenerator(name="secuencia_post", sequenceName = "SEQ_POST", allocationSize=1)
    @Column(name = "ID_COMENTARIO")
    private BigDecimal idComentario;
    @Size(max = 500)
    @Column(name = "TEXTO")
    private String texto;
    @JoinColumn(name = "ID_PROYECTO2", referencedColumnName = "ID_PROYECTO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Proyecto idProyecto2;
    @JoinColumn(name = "ID_PROYECTO1", referencedColumnName = "ID_PROYECTO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Proyecto idProyecto1;
    @JoinColumns({
        @JoinColumn(name = "ID_TAREA1", referencedColumnName = "ID_TAREA"),
        @JoinColumn(name = "ID_PROYECTO3", referencedColumnName = "ID_PROYECTO")})
    @ManyToOne(fetch = FetchType.EAGER)
    private Tarea tarea;
    @JoinColumns({
        @JoinColumn(name = "ID_TAREA", referencedColumnName = "ID_TAREA"),
        @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO")})
    @ManyToOne(fetch = FetchType.EAGER)
    private Tarea tarea1;
    @JoinColumn(name = "NICKNAME", referencedColumnName = "NICKNAME")
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario nickname;

    public Comentario() {
    }

    public Comentario(BigDecimal idComentario) {
        this.idComentario = idComentario;
    }

    public BigDecimal getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(BigDecimal idComentario) {
        this.idComentario = idComentario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Proyecto getIdProyecto2() {
        return idProyecto2;
    }

    public void setIdProyecto2(Proyecto idProyecto2) {
        this.idProyecto2 = idProyecto2;
    }

    public Proyecto getIdProyecto1() {
        return idProyecto1;
    }

    public void setIdProyecto1(Proyecto idProyecto1) {
        this.idProyecto1 = idProyecto1;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public Tarea getTarea1() {
        return tarea1;
    }

    public void setTarea1(Tarea tarea1) {
        this.tarea1 = tarea1;
    }

    public Usuario getNickname() {
        return nickname;
    }

    public void setNickname(Usuario nickname) {
        this.nickname = nickname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComentario != null ? idComentario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comentario)) {
            return false;
        }
        Comentario other = (Comentario) object;
        if ((this.idComentario == null && other.idComentario != null) || (this.idComentario != null && !this.idComentario.equals(other.idComentario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cp.entity.Comentario[ idComentario=" + idComentario + " ]";
    }
    
}
