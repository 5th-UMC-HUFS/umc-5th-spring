package umc.study.converter;

import umc.study.web.dto.TempResponse;

public class TempConverter {

    public static TempResponse.TempTestDto toTempTestDTO() {
        return TempResponse.TempTestDto.builder()
                .testString("This is Test!")
                .build();
    }
}
