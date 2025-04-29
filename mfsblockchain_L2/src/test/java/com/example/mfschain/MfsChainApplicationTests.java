package com.example.mfschain;

import com.example.mfschain.data.SysUser;
import com.example.mfschain.data.SysUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
class MfsChainApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    SysUserRepository sysUserRepository;

    @Test
    void contextLoads2() {

        // 创建实体类
        SysUser sysUser = new SysUser();
        sysUser.setAccount("springdoc");
        sysUser.setEnabled(Boolean.TRUE);
        sysUser.setCreateAt(LocalDateTime.now());

        // 保存实体
        this.sysUserRepository.saveAndFlush(sysUser);

        // 根据自增ID检索实体
        Optional<SysUser> user = this.sysUserRepository.findById(sysUser.getId());

//        logger.info("user={}");
    }

}
