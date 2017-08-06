package cn.edu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */
@Entity
@Table(name = "menu")
public class Menu {
    private Integer id;

    private String name;

    private String url;

    private String icon;

    private Integer parentid;

    private String order;

    private String isheader;

    private String state;
    private List<Role>roles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    @Column(name = "_order")
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getIsheader() {
        return isheader;
    }

    public void setIsheader(String isheader) {
        this.isheader = isheader;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "role_menu",joinColumns = {@JoinColumn(name = "menuid",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "roleid",referencedColumnName = "id")})
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
