package honjay.common.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by honjayChen on 2017/2/25.
 */

public interface ApiService {

    //@GET("/api/value/get")
    //@GET("http://services.odata.org/V3/(S(pq1lpmgz0kuok05ubqtx1c2g))/OData/OData.svc/")
    @GET ("/api/value/get")
    Call<String> getPosts(@Query("param1") String param1,@Query("param2") String param2);

    @Headers({
            "Content-Type: application/json",
            "fileKey: jpeg",
            "fileName: test"
    })
    @POST("/api/values/PostImage") //非同步寫法
    Call<String> uploadFile(@Body String request);

    @GET ("/api/value/ping")
    Call<Object> getPing();
}