package com.ifunyoung.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 验证bean
 */

public class User {
    @NotEmpty(message = "id不能为空")
    @Min(value = 0,message = "id不能小于{value}")
    @Max(value = 100,message = "id不能大于{value}")
    private Integer id;

    @NotEmpty(message = "name不能为空")
    @Size(min = 3,max = 6,message = "name长度必须在{min}-{max}之间")
    private String name;

    @Past // 表示时间必须是一个过去时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    public User() {
    }

    public User(Integer id, String name, Date birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }

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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDay=" + birth +
                '}';
    }
}
