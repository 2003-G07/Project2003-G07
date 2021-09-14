package com.example.demo1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class User implements UserDetails {
    @OneToOne
    Customer customer;
    @Id @GeneratedValue
    protected Long id;
    protected String username;





    protected String password;
    @ManyToMany
    protected List<Role> roles= new ArrayList<>();

   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        for (Role userRole : roles) {
            setAuths.add(new SimpleGrantedAuthority("ROLE_" + userRole));
        }

        return Collections.unmodifiableSet(setAuths);
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public Customer getCustomer() {
        return customer;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }




}
