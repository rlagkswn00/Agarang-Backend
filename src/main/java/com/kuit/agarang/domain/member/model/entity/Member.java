package com.kuit.agarang.domain.member.model.entity;

import com.kuit.agarang.domain.baby.model.entity.Baby;
import com.kuit.agarang.global.common.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "baby_id")
  private Baby baby;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "refresh_token_id")
  private RefreshToken refreshToken;

  private String providerId;

  private String name;
  private String email;
  private String familyRole;

  private String role;


  @Builder
  public Member(String providerId, String name, String email, String role, String familyRole, Baby baby) {
    this.providerId = providerId;
    this.name = name;
    this.email = email;
    this.role = role;
    this.familyRole = familyRole;
    this.baby = baby;
  }

  public Member(Long id) {
    this.id = id;
  }

  public Member changeInfo(String name, String email) {
    return Member.builder()
        .providerId(this.providerId)
        .name(name)
        .email(email)
        .role(this.role)
        .build();
  }

  public Member changeBaby(Baby baby) {
    return Member.builder()
        .providerId(this.providerId)
        .name(this.name)
        .email(this.email)
        .role(this.role)
        .familyRole(this.familyRole)
        .baby(baby)
        .build();
  }

  public static Member of(String providerId, String name, String email, String role) {
    return Member.builder()
        .providerId(providerId)
        .name(name)
        .email(email)
        .role(role)
        .build();
  }
}
