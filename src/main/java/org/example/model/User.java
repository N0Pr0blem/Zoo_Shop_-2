package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
 public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_address",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> address;

    @ManyToMany
    @JoinTable(
            name = "user_product",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    Set<Product> product;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User(){}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

   public User setUsername(String username) {
      this.username = username;
      return this;
   }

   public User setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
   }

   public User setEmail(String email) {
      this.email = email;
      return this;
   }

   public User setPassword(String password) {
      this.password = password;
      return this;
   }

   public User setAddress(Set<Address> address) {
      this.address = address;
      return this;
   }

   public User setProduct(Set<Product> product) {
      this.product = product;
      return this;
   }

   public void setRoles(Set<Role> roles) {
      this.roles = roles;
   }
   public User addRole(Role role) {
      roles.add(role);
      return this;
   }
}
