package com.example.mfschain.data;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "sys_user")
public class SysUser {

    // ID
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 账户
    @Column
    private String account;

    // 是否启用
    @Column
    private Boolean enabled;

    // 创建时间
    @Column
    private LocalDateTime createAt;

}