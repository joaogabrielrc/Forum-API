package com.joaogabrielrc.user.profile;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="profiles")
public class Profile implements GrantedAuthority {

  @Id
  @SequenceGenerator(
    name="profile_sequence",
    sequenceName="profile_sequence",
    allocationSize=1
  )
  @GeneratedValue(
    strategy=SEQUENCE,
    generator="profile_sequence"
  )
  private Long id;

  @Column(name="role")
  private String role;

  private Profile() {
  }

  public Profile(String role) {
    this.role = role;
  }

  @Override
  public String getAuthority() {
    return this.role;
  }

}
