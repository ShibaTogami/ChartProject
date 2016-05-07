/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author shiba
 */
@Entity
@Table(name = "MENSAJE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensaje.findAll", query = "SELECT m FROM Mensaje m"),
    @NamedQuery(name = "Mensaje.findByIdMensaje", query = "SELECT m FROM Mensaje m WHERE m.idMensaje = :idMensaje"),
    @NamedQuery(name = "Mensaje.findByAsunto", query = "SELECT m FROM Mensaje m WHERE m.asunto = :asunto"),
    @NamedQuery(name = "Mensaje.findByMensaje", query = "SELECT m FROM Mensaje m WHERE m.mensaje = :mensaje"),
    @NamedQuery(name = "Mensaje.findByFechaHora", query = "SELECT m FROM Mensaje m WHERE m.fechaHora = :fechaHora")})
public class Mensaje implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MENSAJE")
    private BigDecimal idMensaje;
    @Size(max = 150)
    @Column(name = "ASUNTO")
    private String asunto;
    @Size(max = 1000)
    @Column(name = "MENSAJE")
    private String mensaje;
    @Column(name = "FECHA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @JoinTable(name = "RECIBIENDO", joinColumns = {
        @JoinColumn(name = "MENSAJE_ID_MENSAJE", referencedColumnName = "ID_MENSAJE")}, inverseJoinColumns = {
        @JoinColumn(name = "USUARIO_NICKNAME", referencedColumnName = "NICKNAME")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Usuario> usuarioCollection;
    @ManyToMany(mappedBy = "mensajeCollection1", fetch = FetchType.EAGER)
    private Collection<Usuario> usuarioCollection1;

    public Mensaje() {
    }

    public Mensaje(BigDecimal idMensaje) {
        this.idMensaje = idMensaje;
    }

    public BigDecimal getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(BigDecimal idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection1() {
        return usuarioCollection1;
    }

    public void setUsuarioCollection1(Collection<Usuario> usuarioCollection1) {
        this.usuarioCollection1 = usuarioCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMensaje != null ? idMensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensaje)) {
            return false;
        }
        Mensaje other = (Mensaje) object;
        if ((this.idMensaje == null && other.idMensaje != null) || (this.idMensaje != null && !this.idMensaje.equals(other.idMensaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cp.entity.Mensaje[ idMensaje=" + idMensaje + " ]";
    }
    
}
