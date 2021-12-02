package karbanovich.fit.bstu.newsnew.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import karbanovich.fit.bstu.newsnew.AvailableInternetState;
import karbanovich.fit.bstu.newsnew.ConnectionState;
import karbanovich.fit.bstu.newsnew.Helper.SystemHelper;
import karbanovich.fit.bstu.newsnew.NoInternetState;
import karbanovich.fit.bstu.newsnew.R;

public class NewsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        setData();
    }

    private void setData() {
        Bundle bundle = getIntent().getExtras();
        String category = bundle.getString("category");
        ConnectionState connectionState;

        if(SystemHelper.isNetworkAvailable(getApplicationContext()))
            connectionState = new AvailableInternetState(this, category,
                    (RecyclerView) this.findViewById(R.id.recycler_news));
        else
            connectionState = new NoInternetState(getApplicationContext(), category,
                    (RecyclerView) findViewById(R.id.recycler_news),
                    (TextView) findViewById(R.id.text_notification_check_connection));

        TextView textView = findViewById(R.id.text_category_name);
        textView.setText(category.substring(0,1).toUpperCase() + category.substring(1));

        connectionState.loadData();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }
}