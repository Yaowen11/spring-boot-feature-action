package web.boot.action.entity;

/**
 * @author z
 */
public class JsonData {

    private int code;

    private String message;

    private Object entity;

    public JsonData() {}

    public JsonData(int code) {
        this.code = code;
    }

    public JsonData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonData(int code, String message, Object entity) {
        this.code = code;
        this.message = message;
        this.entity = entity;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }
}
