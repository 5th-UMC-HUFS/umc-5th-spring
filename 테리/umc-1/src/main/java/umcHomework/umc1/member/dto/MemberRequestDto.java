package umcHomework.umc1.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {
    private String name;
    private String nickname;
    private String phoneNum;
    private String bDay;
}
