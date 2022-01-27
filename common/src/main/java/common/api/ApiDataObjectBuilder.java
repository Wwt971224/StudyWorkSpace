package common.api;

/**
 * @author SongWei
 */
public class ApiDataObjectBuilder {

//    public static ApiDataObject<T> newApiDataObject() {
//        return new ApiDataObject<T>();
//    }

    public static <T> ApiDataObject<T> newApiDataObject(T object) {
        return new ApiDataObject<>(object);
    }

}
