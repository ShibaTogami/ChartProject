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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByNickname", query = "SELECT u FROM Usuario u WHERE u.nickname = :nickname"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado"),
    @NamedQuery(name = "Usuario.findByUltimaConexion", query = "SELECT u FROM Usuario u WHERE u.ultimaConexion = :ultimaConexion"),
    @NamedQuery(name = "Usuario.findByFechaRegistro", query = "SELECT u FROM Usuario u WHERE u.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Usuario.findByKarma", query = "SELECT u FROM Usuario u WHERE u.karma = :karma"),
    @NamedQuery(name = "Usuario.findByWebPersonal", query = "SELECT u FROM Usuario u WHERE u.webPersonal = :webPersonal"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findByPregunta", query = "SELECT u FROM Usuario u WHERE u.pregunta = :pregunta"),
    @NamedQuery(name = "Usuario.findByRespuesta", query = "SELECT u FROM Usuario u WHERE u.respuesta = :respuesta")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NICKNAME")
    private String nickname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PASSWORD")
    private String password;
    @Lob
    @Column(name = "AVATAR")
    private Serializable avatar;
    @Size(max = 140)
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "ULTIMA_CONEXION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaConexion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "KARMA")
    private BigInteger karma;
    @Size(max = 100)
    @Column(name = "WEB_PERSONAL")
    private String webPersonal;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Correo electrónico no válido")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 100)
    @Column(name = "PREGUNTA")
    private String pregunta;
    @Size(max = 100)
    @Column(name = "RESPUESTA")
    private String respuesta;
    @ManyToMany(mappedBy = "usuarioCollection", fetch = FetchType.EAGER)
    private Collection<Proyecto> proyectoCollection;
    @ManyToMany(mappedBy = "usuarioCollection", fetch = FetchType.EAGER)
    private Collection<Mensaje> mensajeCollection;
    @JoinTable(name = "USUARIO_TAREA", joinColumns = {
        @JoinColumn(name = "USUARIO_NICKNAME", referencedColumnName = "NICKNAME")}, inverseJoinColumns = {
        @JoinColumn(name = "TAREA_ID_TAREA", referencedColumnName = "ID_TAREA"),
        @JoinColumn(name = "TAREA_ID_PROYECTO", referencedColumnName = "ID_PROYECTO")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Tarea> tareaCollection;
    @JoinTable(name = "ENVIANDO", joinColumns = {
        @JoinColumn(name = "USUARIO_NICKNAME", referencedColumnName = "NICKNAME")}, inverseJoinColumns = {
        @JoinColumn(name = "MENSAJE_ID_MENSAJE", referencedColumnName = "ID_MENSAJE")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Mensaje> mensajeCollection1;
    @OneToMany(mappedBy = "nickname", fetch = FetchType.EAGER)
    private Collection<Comentario> comentarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lider", fetch = FetchType.EAGER)
    private Collection<Proyecto> proyectoCollection1;

    public Usuario() {
    }

    public Usuario(String nickname) {
        this.nickname = nickname;
    }

    public Usuario(String nickname, String password, Date fechaRegistro, String email) {
        this.nickname = nickname;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Serializable getAvatar() {
        return avatar;
    }

    public void setAvatar(Serializable avatar) {
        this.avatar = avatar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(Date ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public BigInteger getKarma() {
        return karma;
    }

    public void setKarma(BigInteger karma) {
        this.karma = karma;
    }

    public String getWebPersonal() {
        return webPersonal;
    }

    public void setWebPersonal(String webPersonal) {
        this.webPersonal = webPersonal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @XmlTransient
    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }

    @XmlTransient
    public Collection<Mensaje> getMensajeCollection() {
        return mensajeCollection;
    }

    public void setMensajeCollection(Collection<Mensaje> mensajeCollection) {
        this.mensajeCollection = mensajeCollection;
    }

    @XmlTransient
    public Collection<Tarea> getTareaCollection() {
        return tareaCollection;
    }

    public void setTareaCollection(Collection<Tarea> tareaCollection) {
        this.tareaCollection = tareaCollection;
    }

    @XmlTransient
    public Collection<Mensaje> getMensajeCollection1() {
        return mensajeCollection1;
    }

    public void setMensajeCollection1(Collection<Mensaje> mensajeCollection1) {
        this.mensajeCollection1 = mensajeCollection1;
    }

    @XmlTransient
    public Collection<Comentario> getComentarioCollection() {
        return comentarioCollection;
    }

    public void setComentarioCollection(Collection<Comentario> comentarioCollection) {
        this.comentarioCollection = comentarioCollection;
    }

    @XmlTransient
    public Collection<Proyecto> getProyectoCollection1() {
        return proyectoCollection1;
    }

    public void setProyectoCollection1(Collection<Proyecto> proyectoCollection1) {
        this.proyectoCollection1 = proyectoCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nickname != null ? nickname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.nickname == null && other.nickname != null) || (this.nickname != null && !this.nickname.equals(other.nickname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cp.entity.Usuario[ nickname=" + nickname + " ]";
    }
    
}
