package karbanovich.fit.bstu.newsnew.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import karbanovich.fit.bstu.newsnew.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView source;
    public ImageView image;
    public CardView container;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.news_item_title);
        source = itemView.findViewById(R.id.news_item_source);
        image = itemView.findViewById(R.id.news_item_image);
        container = itemView.findViewById(R.id.news_item_container);
    }
}
