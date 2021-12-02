package karbanovich.fit.bstu.newsnew;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import karbanovich.fit.bstu.newsnew.Adapter.CustomAdapter;
import karbanovich.fit.bstu.newsnew.Adapter.SelectListener;
import karbanovich.fit.bstu.newsnew.Database.DatabaseBuilder;
import karbanovich.fit.bstu.newsnew.Database.NewsHeadlinesDb;
import karbanovich.fit.bstu.newsnew.Model.NewsApiResponse;
import karbanovich.fit.bstu.newsnew.Model.NewsHeadlines;
import karbanovich.fit.bstu.newsnew.Network.OnFetchDataListener;
import karbanovich.fit.bstu.newsnew.Network.RequestManager;
import karbanovich.fit.bstu.newsnew.View.NewsDetailsActivity;

public class AvailableInternetState extends ConnectionState implements SelectListener {

    private Context context;
    private String category;
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;

    public AvailableInternetState(Context context, String category, RecyclerView recyclerView) {
        this.context = context;
        this.category = category;
        this.recyclerView = recyclerView;
    }


    @Override
    public void loadData() {
        RequestManager requestManager = new RequestManager(context);
        requestManager.getNewsHeadlines(listener, category);
    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            showNews(list);
            CompletableFuture.runAsync(() -> saveRequestAsync(list));
        }

        @Override
        public void onFetchError(String message) { }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
        customAdapter = new CustomAdapter(context, list, this);
        recyclerView.setAdapter(customAdapter);
    }

    private void saveRequestAsync(List<NewsHeadlines> list) {
        SQLiteDatabase db = new DatabaseBuilder(context).getWritableDatabase();

        NewsHeadlinesDb.deleteRowsByCategory(db, category);
        NewsHeadlinesDb.addList(db, list, category);
        db.close();
    }

    @Override
    public void onNewsClicked(NewsHeadlines headline) {
        context.startActivity(new Intent(context, NewsDetailsActivity.class)
                .putExtra("selectedNews", headline)
                .putExtra("category", category)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
