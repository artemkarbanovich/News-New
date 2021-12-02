package karbanovich.fit.bstu.newsnew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;
import karbanovich.fit.bstu.newsnew.Model.NewsHeadlines;
import karbanovich.fit.bstu.newsnew.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {

    private Context context;
    private List<NewsHeadlines> headlines;
    private SelectListener listener;

    public CustomAdapter(Context context, List<NewsHeadlines> headlines, SelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener = listener;
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.news_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        if(headlines.get(position).getTitle() != null)
            holder.title.setText(headlines.get(position).getTitle());
        if(headlines.get(position).getSource().getName() != null)
            holder.source.setText(headlines.get(position).getSource().getName());
        if(headlines.get(position).getUrlToImage() != null)
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.image);

        holder.container.setOnClickListener(view ->
                listener.onNewsClicked(headlines.get(position)));
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
