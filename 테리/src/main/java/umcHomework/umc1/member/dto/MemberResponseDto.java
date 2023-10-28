package umcHomework.umc1.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umcHomework.umc1.member.entity.Member;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDto {
    private Long id;
    private String name;
    private String nickname;
    private String phoneNum;
    private String bDay;

    public static MemberResponseDto of(Member member){
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .nickname(member.getNickname())
                .phoneNum(member.getPhoneNum())
                .bDay(member.getBDay())
                .build();
    }
}
