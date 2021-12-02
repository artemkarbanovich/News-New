package karbanovich.fit.bstu.newsnew.Network;

import karbanovich.fit.bstu.newsnew.Model.NewsApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallNewsApi {

    @GET("top-headlines")
    Call<NewsApiResponse> callHeadlines(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String api_key
    );

    @GET("top-headlines")
    Call<NewsApiResponse> callHeadlines(
            @Query("category") String category,
            @Query("apiKey") String api_key
    );
}
