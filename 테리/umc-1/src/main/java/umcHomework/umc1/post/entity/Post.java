package umcHomework.umc1.post.entity;

import lombok.*;
import umcHomework.umc1.member.entity.Member;
import umcHomework.umc1.post.dto.PostRequestDto;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void updatePost(PostRequestDto postRequestDto){
        if(postRequestDto.getTitle() != null){
            this.title = postRequestDto.getTitle();
        }
        if(postRequestDto.getContent() != null){
            this.content = postRequestDto.getContent();
        }
    }
}