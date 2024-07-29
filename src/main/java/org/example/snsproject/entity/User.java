package org.example.snsproject.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable{

    @NotNull
    private int id;
    private String account;
    private String password;
    @URL
    private String avatar;
    @Email
    private String email;
    private String phone;
    private String nickname;
    @JsonIgnore
    private int status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
