//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.training.springboot.test0100.entity;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
    name = "student"
)
public class StudentModel {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    @JsonView({StudentModel.BaseView.class})
    private Long id;
    @JsonView({StudentModel.BaseView.class})
    private String name;
    @JsonView({StudentModel.BaseView.class})
    private int age;
    @JsonView({StudentModel.DetailView.class})
    private String address;
    @JsonView({StudentModel.DetailView.class})
    private Date birthday;
    private Date createTime;

    public StudentModel() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getAddress() {
        return this.address;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String toString() {
        return "StudentModel(id=" + this.getId() + ", name=" + this.getName() + ", age=" + this.getAge() + ", address=" + this.getAddress() + ", birthday=" + this.getBirthday() + ", createTime=" + this.getCreateTime() + ")";
    }

    public interface DetailView extends StudentModel.BaseView {
    }

    public interface BaseView {
    }

    public interface AllView {
    }
}
