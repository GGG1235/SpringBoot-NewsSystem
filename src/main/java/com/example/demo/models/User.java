package com.example.demo.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.example.demo.models.Authority.getAuthority;

/**
 * 用户实体
 * @author ggg1235
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;


    @NotEmpty(message = "用户名不能为空")
    @Size(min = 4,max = 16)
    @Column(nullable = false,length = 20,unique = true  )
    private String loginName;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6,max = 50)
    @Column(nullable = false,length = 60)
    private String loginPwd;

    @Email(message = "邮箱格式不正确")
    @Column(nullable = false,length = 50)
    private String userEmail;

    @Column(nullable = false,length = 20)
    private String userPhone;

    @Column(nullable = false,length = 2)
    private Integer userType;

    @CreatedDate
    private Date userCreate;

    @LastModifiedDate
    private Date userModified;

    public Long getuserId() {
        return userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    public String getloginName() {
        return loginName;
    }

    public void setloginName(String loginName) {
        this.loginName = loginName;
    }

    public String getloginPwd() {
        return loginPwd;
    }

    public void setloginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getuserEmail() {
        return userEmail;
    }

    public void setuserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getuserPhone() {
        return userPhone;
    }

    public void setuserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getuserType() {
        return userType;
    }

    public void setuserType(Integer userType) {
        this.userType = userType;
    }

    public Date getuserCreate() {
        return userCreate;
    }

    public void setuserCreate(Date userCreate) {
        this.userCreate = userCreate;
    }

    public Date getuserModified() {
        return userModified;
    }

    public void setuserModified(Date userModified) {
        this.userModified = userModified;
    }

    public User(@NotEmpty(message = "用户名不能为空") @Size(min = 4, max = 16) String loginName, @NotEmpty(message = "密码不能为空") @Size(min = 6, max = 20) String loginPwd, @Email(message = "邮箱格式不正确") String userEmail, String userPhone) {
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public User(@NotEmpty(message = "用户名不能为空") @Size(min = 4, max = 16) String loginName, @NotEmpty(message = "密码不能为空") @Size(min = 6, max = 50) String loginPwd, @Email(message = "邮箱格式不正确") String userEmail, String userPhone, Integer userType) {
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userType = userType;
    }

    protected User() {
    }



    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userType=" + userType +
                ", userCreate=" + userCreate +
                ", userModified=" + userModified +
                '}';
    }

    /**
     * SpringSecurity权限相关方法
     */
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<Authority> roles;

    public List<Authority> getRoles() {
        Integer type=this.getuserType();
        List<Authority> roles;
        roles=getAuthority(type);
        return roles;
    }

    public void setRoles(List<Authority> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths=new ArrayList<>();
        List<Authority> roles=this.getRoles();
        for (Authority role:roles){
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return this.loginPwd;
    }

    @Override
    public String getUsername() {
        return this.loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
