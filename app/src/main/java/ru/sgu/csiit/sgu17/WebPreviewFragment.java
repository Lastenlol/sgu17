package ru.sgu.csiit.sgu17;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class WebPreviewFragment extends Fragment {

    private String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.url = getArguments().getString("url");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preview_fragment, container, false);
        WebView webView = (WebView) view.findViewById(R.id.preview_webview);
        webView.loadUrl(url);

        return view;
    }
}
