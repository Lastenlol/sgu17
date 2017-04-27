package ru.sgu.csiit.sgu17;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class NewsItemAdapter extends BaseAdapter {

    private Context context;
    private List<Article> articles;
    private LayoutInflater inflater;

    NewsItemAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = inflater.inflate(R.layout.news_list_item, parent, false);

            ViewHolder holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.description = (TextView) view.findViewById(R.id.description);
            holder.date = (TextView) view.findViewById(R.id.pub_date);

            Article article = (Article) getItem(position);
            holder.title.setText(article.title);
            holder.description.setText(article.description);
            holder.date.setText(article.pubDate);

            view.setTag(holder);
        } else {
            view = convertView;
            ViewHolder holder = (ViewHolder) view.getTag();
        }

        return view;
    }

    private static final class ViewHolder {
        TextView title;
        TextView description;
        TextView date;
    }

}
