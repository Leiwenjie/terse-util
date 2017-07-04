package com.leiwenjie.terse.util.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class TestBean {

    @NotEmpty(message = "名称不可为空")
    @Length(max = 10, message = "最大长度10")
    private String name;

    @Max(value = 150, message = "年龄最大150")
    @NotNull(message = "年龄不可为空")
    private int age;

    @Min(value = 1, message = "性别参数不合法")
    @Max(value = 1, message = "性别参数不合法")
    @NotNull(message = "性别不可为空")
    private int sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TestBean [name=");
        builder.append(name);
        builder.append(", age=");
        builder.append(age);
        builder.append(", sex=");
        builder.append(sex);
        builder.append("]");
        return builder.toString();
    }

}
