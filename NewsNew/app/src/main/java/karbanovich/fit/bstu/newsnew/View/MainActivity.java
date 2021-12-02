package karbanovich.fit.bstu.newsnew.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import karbanovich.fit.bstu.newsnew.Model.GeolocationState;
import karbanovich.fit.bstu.newsnew.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(GeolocationState.getInstance().getState())
            findViewById(R.id.geolocation_container).setVisibility(View.GONE);

        findViewById(R.id.geolocation_btn_later).setOnClickListener(view -> geolocationBtnLaterAction());
        findViewById(R.id.geolocation_btn_activate).setOnClickListener(view -> geolocationBtnActivateAction());

        findViewById(R.id.news_category_general).setOnClickListener(view ->
                startActivity(new Intent(this, NewsListActivity.class)
                .putExtra("category", "general")));
        findViewById(R.id.news_category_business).setOnClickListener(view ->
                startActivity(new Intent(this, NewsListActivity.class)
                        .putExtra("category", "business")));
        findViewById(R.id.news_category_science).setOnClickListener(view ->
                startActivity(new Intent(this, NewsListActivity.class)
                        .putExtra("category", "science")));
        findViewById(R.id.news_category_sport).setOnClickListener(view ->
                startActivity(new Intent(this, NewsListActivity.class)
                        .putExtra("category", "sport")));
        findViewById(R.id.news_category_technology).setOnClickListener(view ->
                startActivity(new Intent(this, NewsListActivity.class)
                        .putExtra("category", "technology")));
        findViewById(R.id.news_category_health).setOnClickListener(view ->
                startActivity(new Intent(this, NewsListActivity.class)
                        .putExtra("category", "health")));
        findViewById(R.id.news_category_entertainment).setOnClickListener(view ->
                startActivity(new Intent(this, NewsListActivity.class)
                        .putExtra("category", "entertainment")));
    }

    private void geolocationBtnLaterAction() {
        GeolocationState.getInstance().setState(false, this);
        hideGeolocationContainer();
    }

    private void geolocationBtnActivateAction() {
        GeolocationState.getInstance().setState(true, this);
        hideGeolocationContainer();
    }

    private void hideGeolocationContainer() {
        RelativeLayout container = findViewById(R.id.geolocation_container);

        container.animate()
                .translationY(Integer.parseInt("-" + container.getHeight()))
                .alpha(0.0f)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        container.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}