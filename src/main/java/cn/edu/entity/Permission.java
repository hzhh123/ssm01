package cn.edu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
@Entity
@Table(name = "permission")
public class Permission implements Serializable{
    private Integer id;
    private Integer parentId;
    private String code;
    private String name;
    private String url;
    private String type;
    private String jsEvent;
    private String icon;
    private String sortCode;
    private String isPublic="0";
    private String isEnable="0";
    private String classname;
    private String remark;
    private Timestamp createtime;
    private Timestamp modifytime;
    private List<Role>roles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJsEvent() {
        return jsEvent;
    }

    public void setJsEvent(String jsEvent) {
        this.jsEvent = jsEvent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    @Column(name = "is_public")
    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }
    @Column(name = "is_enable")
    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Column(name = "remark",length = 1024)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getModifytime() {
        return modifytime;
    }

    public void setModifytime(Timestamp modifytime) {
        this.modifytime = modifytime;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions",fetch = FetchType.LAZY)
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object obj) {
        Permission permission=(Permission)obj;
        if(this.id==permission.id&&this.code.equals(permission.getCode())&&this.url.equals(permission.getUrl())){
            return true;
        }
        return false;
    }


}
