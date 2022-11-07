package com.joaogabrielrc.user;

import com.joaogabrielrc.user.profile.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="users", uniqueConstraints={
  @UniqueConstraint(name="users_email_unique", columnNames="email")
})
public class User implements UserDetails {

  @Id
  @SequenceGenerator(
    name="user_sequence",
    sequenceName="user_sequence",
    allocationSize=1
  )
  @GeneratedValue(
    strategy=SEQUENCE,
    generator="user_sequence"
  )
  private Long id;

  @Column(name="email")
  private String email;

  @Column(name="password")
  private String password;

  @Column(name="name")
  private String name;

  @ManyToMany(fetch=EAGER)
  private final List<Profile> profiles = new ArrayList<>();

  private User() {
  }

  public User(String email, String password, String name) {
    this.email = email;
    this.password = password;
    this.name = name;
  }

  public Long getId() {
    return this.id;
  }

  public String getEmail() {
    return this.email;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.profiles;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.email;
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
