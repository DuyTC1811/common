package io.utilities.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConverterStringUntil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConverterStringUntil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final char[] MAP = new char[65536];

    static {
        // 1) identity
        for (int i = 0; i < MAP.length; i++) {
            MAP[i] = (char) i;
        }

        // 2) điền các biến thể tiếng Việt (nhóm theo nguyên âm) — gọn hơn Map.entry
        mapGroup("ÀÁẠÃẢÂẤẦẨẪẬĂẮẰẲẴẶ", 'A');
        mapGroup("àáạãảâấầẩẫậăắằẳẵặ", 'a');

        mapGroup("ÈÉẺẼẸÊẾỀỂỄỆ", 'E');
        mapGroup("èéẻẽẹêếềểễệ", 'e');

        mapGroup("ÌÍỈĨỊ", 'I');
        mapGroup("ìíỉĩị", 'i');

        mapGroup("ÒÓỌÕỎÔỐỒỔỖỘƠỚỜỞỠỢ", 'O');
        mapGroup("òóọõỏôốồổỗộơớờởỡợ", 'o');

        mapGroup("ÙÚỦŨỤƯỨỪỬỮỰ", 'U');
        mapGroup("ùúủũụưứừửữự", 'u');

        mapGroup("ỲÝỶỸỴ", 'Y');
        mapGroup("ỳýỷỹỵ", 'y');

        MAP['Đ'] = 'D';
        MAP['đ'] = 'd';
    }

    public static <T> T converterStringToObject(String value, Class<T> type) {
        try {
            return objectMapper.readValue(value, type);
        } catch (JsonProcessingException e) {
            LOGGER.error("Converter Err {} Message {}", value, e.getMessage());
        }
        return null;
    }


    public static String converterToString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("Converter Err {} Message {}", object, e.getMessage());
        }
        return null;
    }

    private static void mapGroup(String variants, char base) {
        for (int i = 0; i < variants.length(); i++) {
            MAP[variants.charAt(i)] = base;
        }
    }

    public static String toNoSign(String s) {
        if (s == null || s.isEmpty()) return s;

        // fast-path: nếu toàn ASCII thì trả luôn
        boolean ascii = true;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) > 0x7F) {
                ascii = false;
                break;
            }
        }
        if (ascii) return s;

        char[] out = s.toCharArray();
        for (int i = 0; i < out.length; i++) {
            char c = out[i];
            out[i] = MAP[c];
        }
        return new String(out);
    }

    public static void main(String[] args) {
        String test = "TRẦN CÔNG MINH ĐẠT";
        System.out.println(toNoSign(test));
    }

}
