package umcHomework.umc1.member.entity;

import lombok.*;
import umcHomework.umc1.PostLikes.entity.PostLikes;
import umcHomework.umc1.member.dto.MemberRequestDto;
import umcHomework.umc1.post.entity.Post;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "PHONE")
    private String phoneNum;

    @Column(name = "bday")
    private String bDay;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "memberLikes", cascade = CascadeType.ALL)
    private List<PostLikes> postLikes = new ArrayList<>();

    public void updateMember(MemberRequestDto memberRequestDto){
        if(memberRequestDto.getName() != null){
            this.name = memberRequestDto.getName();
        }
        if(memberRequestDto.getNickname() != null){
            this.nickname = memberRequestDto.getNickname();
        }
    }
}