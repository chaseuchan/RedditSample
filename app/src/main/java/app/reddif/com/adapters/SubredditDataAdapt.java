package app.reddif.com.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import app.reddif.com.R;
import app.reddif.com.activites.ImagePreview;
import app.reddif.com.model.DataModel;

/**
 * Created by chan4u on 8/30/2018.
 */

public class SubredditDataAdapt extends BaseAdapter {

    Context ctx;
    List<DataModel> dataArray = new ArrayList<>();
    public SubredditDataAdapt(Context context,List<DataModel> datalist){
        this.ctx = context;
        this.dataArray = datalist;
    }
    @Override
    public int getCount() {
        return dataArray.size();
    }

    @Override
    public Object getItem(int i) {return i;}

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder;

            holder = new ViewHolder();
            view = LayoutInflater.from(ctx).inflate(R.layout.singledatalist, null);
            holder.txtTitle = view.findViewById(R.id.tvTitle);
            holder.txtAuthor = view.findViewById(R.id.tvAuthor);
            holder.txtUpVotes = view.findViewById(R.id.tvUpVotes);
            holder.txtComments = view.findViewById(R.id.tvComments);
            holder.imgSubreddit = view.findViewById(R.id.imgSubreddit);

        if(dataArray.get(i).getUrl()!=null){
            Glide.with(ctx).load(dataArray.get(i).getUrl()).diskCacheStrategy(DiskCacheStrategy.SOURCE).override(300,300).placeholder(R.drawable.image_failed).into(holder.imgSubreddit);
        }else{
            Glide.with(ctx).load(R.drawable.image_failed).into(holder.imgSubreddit);
        }
        holder.txtComments.setText(dataArray.get(i).getNum_comments()+" Comments");
        holder.txtUpVotes.setText(dataArray.get(i).getUps()+" Up Votes");
        holder.txtAuthor.setText("( "+dataArray.get(i).getAuthor()+" )");
        holder.txtTitle.setText(dataArray.get(i).getTitle());

        holder.imgSubreddit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent passImage = new Intent(ctx, ImagePreview.class);
                passImage.putExtra("imagePreview",dataArray.get(i).getUrl());
                ctx.startActivity(passImage);
            }
        });

        FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
        frameLayout.addView(view);
        return frameLayout;
    }
    class ViewHolder{

        TextView txtTitle,txtAuthor,txtComments,txtUpVotes;
        ImageView imgSubreddit;

    }
}
