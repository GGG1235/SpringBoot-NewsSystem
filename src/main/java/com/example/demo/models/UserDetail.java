package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 用户详情实体
 * @author ggg1235
 */
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long detailId;

    @NotNull(message = "用户id不为空")
    @Column(nullable = false,length = 20,unique = true)
    private Long userId;

    @NotEmpty(message = "用户名不能为空")
    @Size(min = 4,max = 16)
    @Column(nullable = false,length = 20,unique = true  )
    private String userName;

    @Column(nullable = false,length = 20  )
    private String userSex;

    @Column(nullable = false,length = 20  )
    private String userBirthday;

    @Column(nullable = false,length = 20  )
    private String userQQ;

    @Column(nullable = false,length = 20  )
    private String userAddress;

    @Column(nullable = false,length = 20  )
    private String userEducation;

    @Size(min = 0,max = 255)
    @Column(nullable = false,length = 255  )
    private String userHobby;

    @CreatedDate
    private Date detailCreate;

    @LastModifiedDate
    private Date detailModified;

    public Long getdetailId() {
        return detailId;
    }

    public void setdetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Long getuserId() {
        return userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public String getuserSex() {
        return userSex;
    }

    public void setuserSex(String userSex) {
        this.userSex = userSex;
    }


    public String getuserBirthday() {
        return userBirthday;
    }

    public void setuserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getuserQQ() {
        return userQQ;
    }

    public void setuserQQ(String userQQ) {
        this.userQQ = userQQ;
    }

    public String getuserAddress() {
        return userAddress;
    }

    public void setuserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getuserEducation() {
        return userEducation;
    }

    public void setuserEducation(String userEducation) {
        this.userEducation = userEducation;
    }

    public String getuserHobby() {
        return userHobby;
    }

    public void setuserHobby(String userHobby) {
        this.userHobby = userHobby;
    }

    public Date getdetailCreate() {
        return detailCreate;
    }

    public void setdetailCreate(Date detailCreate) {
        this.detailCreate = detailCreate;
    }

    public Date getdetailModified() {
        return detailModified;
    }

    public void setdetailModified(Date detailModified) {
        this.detailModified = detailModified;
    }


    public UserDetail(Long userId) {
        this.userId = userId;
    }

    protected UserDetail() {
    }

    public UserDetail(@NotNull(message = "用户id不为空") @Size(min = 4, max = 16) Long userId, @NotEmpty(message = "用户名不能为空") @Size(min = 4, max = 16) String userName, String userSex, String userBirthday, String userQQ, String userAddress, String userEducation, @Size(min = 0, max = 255) String userHobby) {
        this.userId = userId;
        this.userName = userName;
        this.userSex = userSex;
        this.userBirthday = userBirthday;
        this.userQQ = userQQ;
        this.userAddress = userAddress;
        this.userEducation = userEducation;
        this.userHobby = userHobby;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "detailId=" + detailId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSex=" + userSex +
                ", userBirthday=" + userBirthday +
                ", userQQ='" + userQQ + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userEducation='" + userEducation + '\'' +
                ", userHobby='" + userHobby + '\'' +
                ", detailCreate=" + detailCreate +
                ", detailModified=" + detailModified +
                '}';
    }
}
