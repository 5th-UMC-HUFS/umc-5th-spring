package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TempResponse { //TempResponse가 다양한 응답을 감싸는 역할

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempTestDto{ //TempTestDto는 구체적인 응답데이터 구조를 정의
        String testString;
    }
}
