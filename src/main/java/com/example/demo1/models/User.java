package com.example.demo1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class User implements UserDetails {
    @OneToOne
    Customer customer;
    @Id @GeneratedValue
    protected Long id;

    public Customer getCustomer() {
        return customer;
    }



    protected String password;
    @ManyToMany
    protected List<Role> roles= new ArrayList<>();

  /*  @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        for (String userRole : roles) {
            setAuths.add(new SimpleGrantedAuthority("ROLE_" + userRole));
        }

        return Collections.unmodifiableSet(setAuths);
    }*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }




}
