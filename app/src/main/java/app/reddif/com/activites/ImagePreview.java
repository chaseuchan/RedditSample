package app.reddif.com.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import app.reddif.com.R;

/**
 * Created by chan4u on 8/30/2018.
 */

public class ImagePreview extends AppCompatActivity {
    ImageView imgBackClick,imgPreview;
    String strImagePreview = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_preview);
        imgBackClick = findViewById(R.id.img_click);
        imgPreview = findViewById(R.id.imagePreview);
        Bundle bun = getIntent().getExtras();
        if(bun!=null){
            strImagePreview = bun.getString("imagePreview");
        }
        imgBackClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePreview.this.finish();
            }
        });
        if(imgPreview !=null && !imgPreview.equals("")) {
            Glide.with(ImagePreview.this)
                    .load(strImagePreview)
                    .dontAnimate()
                    .placeholder(R.drawable.image_failed)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(imgPreview);
        }else{
            Glide.with(ImagePreview.this)
                    .load(R.drawable.image_failed)
                    .into(imgPreview);
        }
    }
}
