package com.example.demo.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限实体
 * @author ggg1235
 */
@Entity
public class Authority implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Authority(Integer id, String name) {
        this.name = name;
    }

    static List<Authority> getAuthority(Integer type){

        List<Authority> res=new ArrayList<>();
        String []roles={"","ROLE_USER","ROLE_USER_S","ROLE_ADMIN"};
        if (type==3){
            type=1;
        }else if (type==2){
            type=2;
        }else {
            type=3;
        }
        for (int i = 1; i <= type ; i++) {
            res.add(new Authority(i,roles[i]));
        }
        return res;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
