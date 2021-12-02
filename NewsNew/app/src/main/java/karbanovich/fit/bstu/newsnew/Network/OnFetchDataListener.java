package karbanovich.fit.bstu.newsnew.Network;

import java.util.List;
import karbanovich.fit.bstu.newsnew.Model.NewsHeadlines;

public interface OnFetchDataListener<NewsApiResponse> {

    void onFetchData(List<NewsHeadlines> list, String message);
    void onFetchError(String message);
}
