package honjay.common.utils.Rxbus;

/**
 * Created by honjayChen on 2017/3/25.
 */

public class ErrorBean {

    public ErrorBean(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ErrorBean{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    private String code;
    private String desc;

    public void setCode(String code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}