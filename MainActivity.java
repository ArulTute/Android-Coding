package name;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private AdRequest adRequest;
    private AdView mAdview;
    /* access modifiers changed from: private */
    public InterstitialAd mInterstitialAd;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize((Context) this, getString(R.string.admob_app_id));
        this.mAdview = (AdView) findViewById(R.id.adView);
        this.adRequest = new Builder().build();
        this.mAdview.loadAd(this.adRequest);
        this.mInterstitialAd = new InterstitialAd(this);
        this.mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitial_id));
        this.mInterstitialAd.loadAd(this.adRequest);
        this.adRequest = new Builder().build();

        //TextView foo = (TextView)findViewById(R.id.foo);
        //foo.setText(Html.fromHtml(getString(R.string.nice_html)));


        // TextView c1 = (TextView)findViewById(R.id.c1);
        //   c1.setText(Html.fromHtml(getString(R.string.warning)));

    }


    public void p1(View view) {
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
            this.mInterstitialAd.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                    MainActivity.this.mInterstitialAd.loadAd(new Builder().build());
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.startActivity(new Intent(mainActivity, PageOne.class));
                    MainActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });
            return;
        }
        startActivity(new Intent(this, PageOne.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }




    public static void update(Activity activity) {
        StringBuilder sb = new StringBuilder();
        sb.append("market://details?id=");
        sb.append(activity.getPackageName());
        String str = "android.intent.action.VIEW";
        try {
            activity.startActivity(new Intent(str, Uri.parse(sb.toString())));
        } catch (ActivityNotFoundException unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("http://play.google.com/store/apps/details?id=");
            sb2.append(activity.getPackageName());
            activity.startActivity(new Intent(str, Uri.parse(sb2.toString())));
        }
    }

    public static void rateAction(Activity activity) {
        StringBuilder sb = new StringBuilder();
        sb.append("market://details?id=");
        sb.append(activity.getPackageName());
        String str = "android.intent.action.VIEW";
        try {
            activity.startActivity(new Intent(str, Uri.parse(sb.toString())));
        } catch (ActivityNotFoundException unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("http://play.google.com/store/apps/details?id=");
            sb2.append(activity.getPackageName());
            activity.startActivity(new Intent(str, Uri.parse(sb2.toString())));
        }
    }
}
