package cn.edu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.io.FileUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
@Entity
@Table(name = "role")
public class Role implements Serializable{
    private Integer id;
    private String rolename;
    private String desc;
    private String state;
    private List<Permission>permissions;
    private List<User>users;
    private List<Menu>menus;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Column(name = "_desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @JsonIgnore
    @JoinTable(name = "role_permission",joinColumns = {@JoinColumn(name = "roleid",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permissionid",referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
    @JsonIgnore
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
