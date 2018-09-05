package app.reddif.com.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import app.reddif.com.R;
import app.reddif.com.utils.ConnectionDetector;

/**
 * Created by chan4u on 8/29/2018.
 */

public class SplashScreen extends Activity {

    ConnectionDetector cd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        cd = new ConnectionDetector(SplashScreen.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(cd.isConnectingToInternet()){
                    Intent new_Intent = new Intent(SplashScreen.this, TabbedMainActivity.class);
                    startActivity(new_Intent);
                    SplashScreen.this.finish();
                }else{
                    Intent new_Intent = new Intent(SplashScreen.this, TabbedMainActivity.class);
                    startActivity(new_Intent);
                    Toast.makeText(SplashScreen.this,"Check your Internet Connection",Toast.LENGTH_SHORT).show();
                }
            }
        }, 3000);


    }
}
