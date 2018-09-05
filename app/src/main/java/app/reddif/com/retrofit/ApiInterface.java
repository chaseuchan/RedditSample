package app.reddif.com.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by chan4u on 8/29/2018.
 */

public interface ApiInterface
{
    @GET("{feed_name}/.json")
    Call<ResponseBody> getFeed(@Path("feed_name") String feed_name);
}
