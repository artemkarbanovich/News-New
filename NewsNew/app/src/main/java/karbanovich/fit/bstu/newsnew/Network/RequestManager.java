package karbanovich.fit.bstu.newsnew.Network;

import android.content.Context;
import android.widget.Toast;
import karbanovich.fit.bstu.newsnew.Model.GeolocationState;
import karbanovich.fit.bstu.newsnew.Model.NewsApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {

    private final static String API_KEY = "6c02ee7f4b2049d4a863c4226fee263b";
    private Context context;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }


    public void getNewsHeadlines(OnFetchDataListener listener, String category) {

        Call<NewsApiResponse> call;
        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);
        GeolocationState state = GeolocationState.getInstance();

        if(state.getState() && state.getCountryCode() != null)
            call = callNewsApi.callHeadlines(state.getCountryCode(), category, API_KEY);
        else
            call = callNewsApi.callHeadlines(category, API_KEY);

        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if(!response.isSuccessful())
                        Toast.makeText(context, "Ошибка получения новостей", Toast.LENGTH_SHORT).show();
                    listener.onFetchData(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    Toast.makeText(context, "Ошибка получения новостей", Toast.LENGTH_SHORT).show();
                    listener.onFetchError("Ошибка получения новостей");
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, "Ошибка получения новостей", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
