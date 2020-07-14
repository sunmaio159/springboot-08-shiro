package com.sun.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //shiroFilterFactoryBean
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager webSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(webSecurityManager);
        /*
         anon:无需认证就可以访问
         authc：必须认证了才能访问
         user:必须拥有  记住我  功能才能用
         perms:拥有对某个资源的权限才能访问
         role:拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        //拦截
        filterMap.put("/add","authc");
        filterMap.put("/update","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        //设置登录请求
        shiroFilterFactoryBean.setLoginUrl("/toLogin");

        return shiroFilterFactoryBean;
    }

    //DefaultWevSecurityManager   2
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm 对象   1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
