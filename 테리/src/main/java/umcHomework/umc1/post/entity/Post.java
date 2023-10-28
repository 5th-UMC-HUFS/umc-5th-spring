package umcHomework.umc1.post.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import umcHomework.umc1.PostLikes.entity.PostLikes;
import umcHomework.umc1.member.entity.Member;
import umcHomework.umc1.post.dto.PostRequestDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "postLikes")
    private List<PostLikes> postLikes = new ArrayList<>();

    @ColumnDefault("0")
    @Column(name = "count_likes", nullable = false)
    private int countLikes;

    public void updatePost(PostRequestDto postRequestDto){
        if(postRequestDto.getTitle() != null){
            this.title = postRequestDto.getTitle();
        }
        if(postRequestDto.getContent() != null){
            this.content = postRequestDto.getContent();
        }
    }
}