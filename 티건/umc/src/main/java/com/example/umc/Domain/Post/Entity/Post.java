package com.example.umc.Domain.Post.Entity;

import com.example.umc.Domain.Post.DTO.PostUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static org.springframework.util.StringUtils.hasText;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREATEDATE")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "UPDATEDATE")
    @LastModifiedDate
    private LocalDateTime updateDate;

    @Column(name = "MEMBER_ID")
    private Long memberId;

    public void update(PostUpdateRequestDto postUpdateRequestDto){
        if(hasText(postUpdateRequestDto.getTitle())){
            this.title = postUpdateRequestDto.getTitle();
        }
        if(hasText(postUpdateRequestDto.getContent())){
            this.content = postUpdateRequestDto.getContent();
        }
    }

}
