package karbanovich.fit.bstu.newsnew.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import karbanovich.fit.bstu.newsnew.Helper.FileTxtHelper;
import karbanovich.fit.bstu.newsnew.Helper.MailHelper;
import karbanovich.fit.bstu.newsnew.Model.NewsHeadlines;
import karbanovich.fit.bstu.newsnew.R;

public class NewsDetailsActivity extends AppCompatActivity {

    private String category;
    private NewsHeadlines selectedNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        setData();
    }

    private void setData() {
        category = getIntent().getExtras().getString("category");
        selectedNews = (NewsHeadlines) getIntent().getSerializableExtra("selectedNews");

        if(selectedNews.getUrlToImage() != null)
            Picasso.get().load(selectedNews.getUrlToImage())
                    .into(((ImageView) findViewById(R.id.selected_news_image)));
        if(selectedNews.getPublishedAt() != null)
            ((TextView) findViewById(R.id.selected_news_published_at))
                    .setText(selectedNews.getPublishedAt()
                            .replace("T", " ")
                            .replace("Z", " "));
        if(selectedNews.getTitle() != null)
            ((TextView) findViewById(R.id.selected_news_title)).setText(selectedNews.getTitle());
        if(selectedNews.getDescription() != null)
            ((TextView) findViewById(R.id.selected_news_description)).setText(selectedNews.getDescription());
        if(selectedNews.getContent() != null)
            ((TextView) findViewById(R.id.selected_news_content)).setText(selectedNews.getContent());

        ((TextView) findViewById(R.id.selected_news_read_more)).setOnClickListener(view ->
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(selectedNews.getUrl()))));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_send_by_email) {
            MailHelper.sendEmail(this, FileTxtHelper.createNewsFile(this, selectedNews, category));
            return true;
        } else if(item.getItemId() == R.id.action_share_news) {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Вы должны это увидеть!");
            i.putExtra(Intent.EXTRA_TEXT, selectedNews.getUrl());
            startActivity(Intent.createChooser(i, "Share news"));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, NewsListActivity.class)
                .putExtra("category", category));
    }
}