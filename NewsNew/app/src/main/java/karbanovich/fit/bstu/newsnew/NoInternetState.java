package karbanovich.fit.bstu.newsnew;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import karbanovich.fit.bstu.newsnew.Adapter.CustomAdapter;
import karbanovich.fit.bstu.newsnew.Adapter.SelectListener;
import karbanovich.fit.bstu.newsnew.Database.DatabaseBuilder;
import karbanovich.fit.bstu.newsnew.Database.NewsHeadlinesDb;
import karbanovich.fit.bstu.newsnew.Model.NewsHeadlines;
import karbanovich.fit.bstu.newsnew.View.NewsDetailsActivity;

public class NoInternetState extends ConnectionState implements SelectListener {

    private Context context;
    private String category;
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private TextView notification;

    public NoInternetState(Context context, String category, RecyclerView recyclerView, TextView notification) {
        this.context = context;
        this.category = category;
        this.recyclerView = recyclerView;
        this.notification = notification;
    }


    @Override
    public void loadData() {
        SQLiteDatabase db = new DatabaseBuilder(context).getWritableDatabase();

        if(NewsHeadlinesDb.getCountOfRowsByCategory(db, category) == 0) {
            recyclerView.setVisibility(View.GONE);
            notification.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
            customAdapter = new CustomAdapter(context, NewsHeadlinesDb.getNewsByCategory(db, category), this);
            recyclerView.setAdapter(customAdapter);
        }
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
