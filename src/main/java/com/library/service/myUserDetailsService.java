package com.library.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/2/26.
 */
@Service
public class myUserDetailsService extends BaseService implements UserDetailsService{

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return getUserBaseDao().byHql("FROM User as u WHERE u.name = '" + s + "'");
    }

}
