package com.example.demo.models;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 文件实体
 * @author ggg1235
 */
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fileId;

    @Column(nullable = false,length = 20)
    private Long newsId;

    @Column(nullable = false,length = 100)
    private String fileName;

    @Column(nullable = false,length = 100)
    private String originName;

    @Column(nullable = false,length = 50)
    private BigDecimal fileSize;

    @Column(nullable = false,length = 200)
    private String filePath;

    @Column(nullable = false,length = 20)
    private Long userId;

    @CreatedDate
    private Date fileCreate;

    @Column(nullable = false,length = 20)
    private Integer fileType;

    public Long getfileId() {
        return fileId;
    }

    public void setfileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getnewsId() {
        return newsId;
    }

    public void setnewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getfileName() {
        return fileName;
    }

    public void setfileName(String fileName) {
        this.fileName = fileName;
    }

    public String getoriginName() {
        return originName;
    }

    public void setoriginName(String originName) {
        this.originName = originName;
    }

    public BigDecimal getfileSize() {
        return fileSize;
    }

    public void setfileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    public String getfilePath() {
        return filePath;
    }

    public void setfilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getuserId() {
        return userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    public Date getfileCreate() {
        return fileCreate;
    }

    public void setfileCreate(Date fileCreate) {
        this.fileCreate = fileCreate;
    }

    public Integer getfileType() {
        return fileType;
    }

    public void setfileType(Integer fileType) {
        this.fileType = fileType;
    }

    public File(Long newsId, String fileName, String originName, BigDecimal fileSize, String filePath, Long userId, Date fileCreate, Integer fileType) {
        this.newsId = newsId;
        this.fileName = fileName;
        this.originName = originName;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.userId = userId;
        this.fileCreate = fileCreate;
        this.fileType = fileType;
    }

    public File(String fileName, String filePath, Integer fileType) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
    }

    public File(String fileName, String originName, BigDecimal fileSize, String filePath, Long userId, Integer fileType,Date fileCreate) {
        this.fileName = fileName;
        this.originName = originName;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.userId = userId;
        this.fileType = fileType;
        this.fileCreate=fileCreate;
    }

    protected File() {
    }

    @Override
    public String toString() {
        return "File{" +
                "fileId=" + fileId +
                ", newsId=" + newsId +
                ", fileName='" + fileName + '\'' +
                ", originName='" + originName + '\'' +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                ", userId=" + userId +
                ", fileCreate=" + fileCreate +
                ", fileType=" + fileType +
                '}';
    }
}
