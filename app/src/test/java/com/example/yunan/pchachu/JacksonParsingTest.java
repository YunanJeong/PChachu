package com.example.yunan.pchachu;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class JacksonParsingTest {

    ModelAddressParser ap = new ModelAddressParser();

    @Test
    public void testJacksonParsing() {

        String searchResult = "[\n" +
                "    {\n" +
                "        \"Pc_name\": \"더킹 pc방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"애플피시방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"nupc\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"카카오PC클럽\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"먹방피시방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"짱PC클럽\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"갤러리PC 대구지산점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"마블PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"밴드PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"노블레스pc\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"더원PC범어점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"통PC\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"피에스타PC방 진천점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"하이엔PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"욜로pc대곡점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"헬로우먹방PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"블랙홀PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"어벤져스PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"PC CASTLE\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"아이파크PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"프린스PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"아이넷 동천점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"샹떼PC방 경주성건점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"컴짱PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"화이트PC카페\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"욜로PC카페 경주본점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"PC방코코\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"피카츄PC방 동천점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"피카츄PC방 성건점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"피카츄PC방 황오점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"파이리PC방 동천점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"파이리PC방 성건점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"파이리PC방 황오점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"꼬부기PC방 동천점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"꼬부기PC방 성건점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"꼬부기PC방 황오점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"야도란PC방 동천점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"야도란PC방 성건점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"야도란PC방 황오점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"둥지PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"브로스PC카페\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"쓰리팝PC방 전농동\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"쓰리팝PC방 한신대점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"쓰리팝PC방대치점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"쓰리팝PC방이문동점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"아이리스 PC방치평점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"아이리스PC방 상무점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"아이맥스 PC방 광주점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"아프리카TV PC방 광주점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"애플PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"앤젤PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"엑소 PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"오즈쓰리팝PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"제우스탑PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"포포PC방건대점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"아프리카TV 오픈스튜디오 PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"아프리카TV 오픈스튜디오 PC방 잠실점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"아프리카TV 오픈스튜디오 PC방 대전점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"아이리스PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"영자PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"레전드PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"펀펀PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"앤유피시\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"PC지엥 이대역점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"퍼플 PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"갤럭시 PC방 아주대점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"스토리온 PC방 아주대점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"갤럭시 PC방 성대점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"쓰리팝 PC방 성대점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"쓰리팝 PC방 수지점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"쓰리팝 PC방 이제동점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"쓰리팝 PC방 이영호점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"갤럭시 PC방 구성훈점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"갤럭시 PC방 박준오점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"갤럭시 PC방 이제동점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"스토리온 PC방 이대역점\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"스타크래프트 PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"리그오브레전드 PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"배틀그라운드 PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"오버워치 PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"문명 PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"메이플스토리 PC방\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"Pc_name\": \"리니지 PC방\"\n" +
                "    }\n" +
                "]";
        //ModelCafe cafe = new ModelCafe();
        List<Map<String,Object>> cafes=null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            //cafe = mapper.readValue(searchResult, ModelCafe.class);
            cafes = mapper.readValue(searchResult, new TypeReference<List<Map<String,Object>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }


        assertEquals("더킹 pc방", cafes.get(0).get("Pc_name").toString());
    }


}