/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author shiba
 */
@Entity
@Table(name = "TAREA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarea.findAll", query = "SELECT t FROM Tarea t"),
    @NamedQuery(name = "Tarea.findByIdTarea", query = "SELECT t FROM Tarea t WHERE t.tareaPK.idTarea = :idTarea"),
    @NamedQuery(name = "Tarea.findByNombre", query = "SELECT t FROM Tarea t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tarea.findByFechaInicio", query = "SELECT t FROM Tarea t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Tarea.findByDescripcion", query = "SELECT t FROM Tarea t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tarea.findByChangeLog", query = "SELECT t FROM Tarea t WHERE t.changeLog = :changeLog"),
    @NamedQuery(name = "Tarea.findByEstado", query = "SELECT t FROM Tarea t WHERE t.estado = :estado"),
    @NamedQuery(name = "Tarea.findByPrioridad", query = "SELECT t FROM Tarea t WHERE t.prioridad = :prioridad"),
    @NamedQuery(name = "Tarea.findByIdProyecto", query = "SELECT t FROM Tarea t WHERE t.tareaPK.idProyecto = :idProyecto")})
public class Tarea implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TareaPK tareaPK;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 500)
    @Column(name = "CHANGE_LOG")
    private String changeLog;
    @Size(max = 30)
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "PRIORIDAD")
    private BigInteger prioridad;
    @ManyToMany(mappedBy = "tareaCollection", fetch = FetchType.EAGER)
    private Collection<Usuario> usuarioCollection;
    @OneToMany(mappedBy = "tarea", fetch = FetchType.EAGER)
    private Collection<Comentario> comentarioCollection;
    @OneToMany(mappedBy = "tarea1", fetch = FetchType.EAGER)
    private Collection<Comentario> comentarioCollection1;
    @JoinColumn(name = "ID_PROYECTO", referencedColumnName = "ID_PROYECTO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Proyecto proyecto;

    public Tarea() {
    }

    public Tarea(TareaPK tareaPK) {
        this.tareaPK = tareaPK;
    }

    public Tarea(BigInteger idTarea, BigInteger idProyecto) {
        this.tareaPK = new TareaPK(idTarea, idProyecto);
    }

    public TareaPK getTareaPK() {
        return tareaPK;
    }

    public void setTareaPK(TareaPK tareaPK) {
        this.tareaPK = tareaPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigInteger getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(BigInteger prioridad) {
        this.prioridad = prioridad;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @XmlTransient
    public Collection<Comentario> getComentarioCollection() {
        return comentarioCollection;
    }

    public void setComentarioCollection(Collection<Comentario> comentarioCollection) {
        this.comentarioCollection = comentarioCollection;
    }

    @XmlTransient
    public Collection<Comentario> getComentarioCollection1() {
        return comentarioCollection1;
    }

    public void setComentarioCollection1(Collection<Comentario> comentarioCollection1) {
        this.comentarioCollection1 = comentarioCollection1;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tareaPK != null ? tareaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarea)) {
            return false;
        }
        Tarea other = (Tarea) object;
        if ((this.tareaPK == null && other.tareaPK != null) || (this.tareaPK != null && !this.tareaPK.equals(other.tareaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cp.entity.Tarea[ tareaPK=" + tareaPK + " ]";
    }
    
}
