package umcHomework.umc1.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umcHomework.umc1.member.entity.Member;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    private String title;
    private String content;
    private Member member;  //여기 수정해야 할 것 같습니당.... member 객체 말고 userId로 접근할 수 있게.
}
