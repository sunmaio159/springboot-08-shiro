package com.sun.config;

import com.sun.pojo.User;
import com.sun.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=》授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //数据库获取权限进行授权，---真实业务
        info.addStringPermission("user:add");
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=》认证doGetAuthenticationInfo");
        //用户名，密码~ 数据库中取
        String name = "root";
        String password = "123";
        UsernamePasswordToken userToken= (UsernamePasswordToken)authenticationToken;
        User user = userService.queryUserByName(userToken.getUsername());
        if(user==null){
            return null;
        }

        return new SimpleAuthenticationInfo("",user.getPwd(),"");
    }
}
