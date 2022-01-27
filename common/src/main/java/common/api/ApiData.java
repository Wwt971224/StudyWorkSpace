package common.api;

/**
 * @author SongWei
 */
public interface ApiData<T> {

    /**
     * 返回的数据信息
     *
     * @return
     */
    T getData();

    /**
     * 返回数据的状态码
     *
     * @return
     */
    default Integer getCode() {
        return DataStatus.OK.getCode();
    }

    /**
     * 返回数据的描述信息
     *
     * @return
     */
    default String getMessage() {
        return DataStatus.OK.getMessage();
    }

    enum DataStatus {
        /**
         * 正确的数据状态
         */
        OK(0, "ok"),
        /**
         * 错误的数据状态
         */
        ERROR(1, "error"),
        ;
        private final int code;
        private final String message;

        DataStatus(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

}
