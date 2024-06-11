package kz.sm.webcallsdksample;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import kz.sm.webcallsdk.items.EventListener;
import kz.sm.webcallsdk.items.WebViewProvider;

public class MainActivity extends AppCompatActivity {

    private final EventListener listener = () -> Log.i("MainActivity", "onCallFinished");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);
        Button start = findViewById(R.id.start);

        WebViewProvider provider = WebViewProvider.getProvider();
        provider.setWebView(this, webView, false);
        provider.setEventListener(listener);

        start.setOnClickListener(view -> {
            webView.setVisibility(View.VISIBLE);
            if (provider.loadPage())
                start.setVisibility(View.GONE);
        });

        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                webView.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);
            }
        });
    }
}