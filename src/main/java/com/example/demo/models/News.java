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
 * 新闻实体
 * @author ggg1235
 */
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long newsId;

    @NotEmpty(message = "新闻标题不为空")
    @Size(max = 60)
    @Column(nullable = false,length = 50)
    private String newsTitle;

    @NotNull(message = "发布者不为空")
    @Column(nullable = false,length = 20)
    private Long userId;

    @NotEmpty(message = "新闻内容不为空")
    @Size(min = 10,max = 100000)
    @Column(nullable = false,length = 10000)
    private String newsContent;

    @Column(nullable = false,length = 50)
    private String newsWord;

    @Column(nullable = false,length = 20)
    private Date auditTime;

    @Column(nullable = false,length = 20)
    private Integer auditEditor;

    @NotNull(message = "新闻类型不能为空")
    @Column(nullable = false,length = 2)
    private Integer newsType;

    @NotNull(message = "状态类型不能为空")
    @Column(nullable = false,length = 20)
    private Integer newsStatus;

    @CreatedDate
    private Date newsCreate;

    @LastModifiedDate
    private Date newsModified;

    public Long getnewsId() {
        return newsId;
    }

    public void setnewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getnewsTitle() {
        return newsTitle;
    }

    public void setnewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public Long getuserId() {
        return userId;
    }

    public void setuserId(Long userId) {
        this.userId = userId;
    }

    public String getnewsContent() {
        return newsContent;
    }

    public void setnewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getnewsWord() {
        return newsWord;
    }

    public void setnewsWord(String newsWord) {
        this.newsWord = newsWord;
    }

    public Date getauditTime() {
        return auditTime;
    }

    public void setauditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getauditEditor() {
        return auditEditor;
    }

    public void setauditEditor(Integer auditEditor) {
        this.auditEditor = auditEditor;
    }

    public Integer getnewsType() {
        return newsType;
    }

    public void setnewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public Integer getnewsStatus() {
        return newsStatus;
    }

    public void setnewsStatus(Integer newsStatus) {
        this.newsStatus = newsStatus;
    }

    public Date getnewsCreate() {
        return newsCreate;
    }

    public void setnewsCreate(Date newsCreate) {
        this.newsCreate = newsCreate;
    }

    public Date getnewsModified() {
        return newsModified;
    }

    public void setnewsModified(Date newsModified) {
        this.newsModified = newsModified;
    }

    public News(String newsTitle, Long userId, String newsContent, String newsWord, Date auditTime, Integer auditEditor, Integer newsType, Integer newsStatus, Date newsCreate, Date newsModified) {
        this.newsTitle = newsTitle;
        this.userId = userId;
        this.newsContent = newsContent;
        this.newsWord = newsWord;
        this.auditTime = auditTime;
        this.auditEditor = auditEditor;
        this.newsType = newsType;
        this.newsStatus = newsStatus;
        this.newsCreate = newsCreate;
        this.newsModified = newsModified;
    }

    public News(String newsTitle, Long userId, String newsContent, String newsWord) {
        this.newsTitle = newsTitle;
        this.userId = userId;
        this.newsContent = newsContent;
        this.newsWord = newsWord;
    }

    public News(@NotEmpty(message = "新闻标题不为空") @Size(max = 16) String newsTitle, @NotNull(message = "发布者不为空") Long userId, @NotEmpty(message = "新闻内容不为空") @Size(min = 10, max = 5000) String newsContent, String newsWord, Integer auditEditor, @NotNull(message = "新闻类型不能为空") Integer newsType, @NotNull(message = "状态类型不能为空") Integer newsStatus) {
        this.newsTitle = newsTitle;
        this.userId = userId;
        this.newsContent = newsContent;
        this.newsWord = newsWord;
        this.auditEditor = auditEditor;
        this.newsType = newsType;
        this.newsStatus = newsStatus;
    }

    protected News() {
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", newsTitle='" + newsTitle + '\'' +
                ", userId=" + userId +
                ", newsWord='" + newsWord + '\'' +
                ", auditTime=" + auditTime +
                ", auditEditor=" + auditEditor +
                ", newsType=" + newsType +
                ", newsStatus=" + newsStatus +
                ", newsCreate=" + newsCreate +
                ", newsModified=" + newsModified +
                '}';
    }
}
