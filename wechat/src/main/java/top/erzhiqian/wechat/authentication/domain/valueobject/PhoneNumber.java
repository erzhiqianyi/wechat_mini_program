package top.erzhiqian.wechat.authentication.domain.valueobject;

import lombok.ToString;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.stream.Stream;

@ToString
public class PhoneNumber {
    private String number;
    private Area area;

    public PhoneNumber(String number, String code) {
        if (StringUtils.isEmpty(number)) {
            throw new IllegalArgumentException("手机号不能为空。");
        }
        this.number = number;
        this.area = Area.fromCode(code);
        if (null == code) {
            throw new IllegalArgumentException("手机格式不正确不能为空。");
        }
        boolean validPhone = this.area.checkFormat(this.number);
        if (!validPhone) {
            throw new IllegalArgumentException("手机号格式错误。");
        }
    }

    public enum Area {
        CHINA("86") {
            @Override
            public boolean checkFormat(String phoneNumber) {
                if (StringUtils.isEmpty(phoneNumber)) {
                    return false;
                }
                if (phoneNumber.length() != 11) {
                    return false;
                }
                return phoneNumber.startsWith("1");
            }
        };
        private String code;

        Area(String code) {
            this.code = code;
        }

        public static Area fromCode(String code) {
            Optional<Area> valueCode = Stream.of(values())
                    .filter(item -> item.code.equals(code))
                    .findFirst();
            if (valueCode.isPresent()) {
                return valueCode.get();
            } else {
                return null;
            }
        }

        public abstract boolean checkFormat(String phoneNumber);
    }

    public String number() {
        return number;
    }
}
