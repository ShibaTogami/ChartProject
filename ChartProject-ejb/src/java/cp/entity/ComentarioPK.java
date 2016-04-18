/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author shiba
 */
@Embeddable
public class ComentarioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_COMENTARIO")
    private BigInteger idComentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TAREA")
    private BigInteger idTarea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROYECTO")
    private BigInteger idProyecto;

    public ComentarioPK() {
    }

    public ComentarioPK(BigInteger idComentario, BigInteger idTarea, BigInteger idProyecto) {
        this.idComentario = idComentario;
        this.idTarea = idTarea;
        this.idProyecto = idProyecto;
    }

    public BigInteger getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(BigInteger idComentario) {
        this.idComentario = idComentario;
    }

    public BigInteger getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(BigInteger idTarea) {
        this.idTarea = idTarea;
    }

    public BigInteger getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComentario != null ? idComentario.hashCode() : 0);
        hash += (idTarea != null ? idTarea.hashCode() : 0);
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComentarioPK)) {
            return false;
        }
        ComentarioPK other = (ComentarioPK) object;
        if ((this.idComentario == null && other.idComentario != null) || (this.idComentario != null && !this.idComentario.equals(other.idComentario))) {
            return false;
        }
        if ((this.idTarea == null && other.idTarea != null) || (this.idTarea != null && !this.idTarea.equals(other.idTarea))) {
            return false;
        }
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cp.entity.ComentarioPK[ idComentario=" + idComentario + ", idTarea=" + idTarea + ", idProyecto=" + idProyecto + " ]";
    }
    
}
