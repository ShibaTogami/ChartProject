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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

/**
 *
 * @author shiba
 */
@Embeddable
public class TareaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "secuencia_tarea")
    @SequenceGenerator(name="secuencia_tarea", sequenceName = "SEQ_TAR", allocationSize=1)
    @Column(name = "ID_TAREA")
    private BigInteger idTarea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROYECTO")
    private BigInteger idProyecto;

    public TareaPK() {
    }

    public TareaPK(BigInteger idTarea, BigInteger idProyecto) {
        this.idTarea = idTarea;
        this.idProyecto = idProyecto;
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
        hash += (idTarea != null ? idTarea.hashCode() : 0);
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TareaPK)) {
            return false;
        }
        TareaPK other = (TareaPK) object;
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
        return "cp.entity.TareaPK[ idTarea=" + idTarea + ", idProyecto=" + idProyecto + " ]";
    }
    
}
