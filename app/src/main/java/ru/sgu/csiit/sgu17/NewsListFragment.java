package ru.sgu.csiit.sgu17;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NewsListFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Article>> {

    private static final String LOG_TAG = "NewsListFragment";

    private final ArrayList<Article> data = new ArrayList<>();
    private NewsItemAdapter dataAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.dataAdapter = new NewsItemAdapter(this.getActivity(), data);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_list_item, container, false);
        ListView newsList = (ListView) view.findViewById(R.id.news_list_fragment);
        newsList.setAdapter(dataAdapter);
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Article article = (Article) parent.getItemAtPosition(position);

                FragmentManager fragmentManager = getFragmentManager();
                WebPreviewFragment fragment = new WebPreviewFragment();
                Bundle args = new Bundle();
                args.putString("url", article.link);
                fragment.setArguments(args);
                fragmentManager
                        .beginTransaction()
                        .add(R.id.container, fragment)
                        .addToBackStack(null)
                        .commit();

            }
        });

        Button refreshBtn = (Button) view.findViewById(R.id.refresh_btn);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoaderManager().restartLoader(0, null, NewsListFragment.this);
            }
        });

        return view;
    }

    @Override
    public Loader<List<Article>> onCreateLoader(int id, Bundle args) {
        Log.d(LOG_TAG, "onCreateLoader");
        return new SguRssLoader(this.getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> loaderData) {
        Log.d(LOG_TAG, "onLoadFinished " + loader.hashCode());
        data.clear();
        data.addAll(loaderData);
        dataAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        Log.d(LOG_TAG, "onLoaderReset " + loader.hashCode());
    }

}
