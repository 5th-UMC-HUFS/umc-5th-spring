package com.example.umc.domain.member.entity;

import com.example.umc.domain.member.dto.MemberUpdateRequestDto;
import com.example.umc.domain.post.entity.Post;
import com.example.umc.domain.postlike.entity.PostLike;
import com.example.umc.global.entity.CreateUpdateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends CreateUpdateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NICKNAME")
    private String nickName;

    @Column(name = "PHONENUMBER")
    private String phoneNumber;

    @Column(name = "BIRTHDAY")
    private String birthDay;

    // 게시글과 맵핑
    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PostLike> postLikeList = new ArrayList<>();

    public void update(MemberUpdateRequestDto memberUpdateDto) {
        if (hasText(memberUpdateDto.getName())) {
            this.name = memberUpdateDto.getName();
        }
        if (hasText(memberUpdateDto.getPhoneNumber())) {
            this.phoneNumber = memberUpdateDto.getPhoneNumber();
        }
        if (hasText(memberUpdateDto.getNickName())) {
            this.nickName = memberUpdateDto.getNickName();
        }

    }

    public void like(PostLike postLike){
        this.postLikeList.add(postLike);
    }

    public void disLike(PostLike postLike){
        this.postLikeList.remove(postLike);
    }
}
