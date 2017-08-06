package cn.edu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27.
 */
@Entity
@Table(name = "user")
public class User implements Serializable{
    private Integer id;
    private String username;
    private String password;
    private String state;
    private Timestamp createtime;
    private Timestamp updatetime;
    private List<Role> roles;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "createtime")
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "updatetime")
    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }
    @JsonIgnore
    @JoinTable(name = "user_role",joinColumns = {@JoinColumn(name = "userid",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "roleid",referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


}
