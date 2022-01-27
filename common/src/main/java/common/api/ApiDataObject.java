package common.api;

import lombok.Data;

/**
 * @author SongWei
 */
@Data
public class ApiDataObject<T> implements ApiData<T> {

    private T data;

    private Integer code;

    private String message;

    public ApiDataObject() {
        this(null, DataStatus.OK.getCode(), DataStatus.OK.getMessage());
    }

    public ApiDataObject(T data) {
        this(data, DataStatus.OK.getCode(), DataStatus.OK.getMessage());
    }

    public ApiDataObject(Integer code, String message) {
        this(null, code, message);
    }

    public ApiDataObject(T data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public static <R> ApiDataObject<R> success() {
        return new ApiDataObject<>();
    }

    public static <R> ApiDataObject<R> success(R data) {
        return new ApiDataObject<>(data);
    }

    public static <R> ApiDataObject<R> fail() {
        return new ApiDataObject<>(DataStatus.ERROR.getCode(), DataStatus.ERROR.getMessage());
    }

    public static <R> ApiDataObject<R> fail(Integer code, String message) {
        return new ApiDataObject<>(code, message);
    }
}
