package app.reddif.com.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.reddif.com.R;
import app.reddif.com.adapters.SubredditDataAdapt;
import app.reddif.com.model.DataModel;
import app.reddif.com.retrofit.ApiClient;
import app.reddif.com.retrofit.ApiInterface;
import app.reddif.com.roomdb.RoomDb;
import app.reddif.com.utils.ConnectionDetector;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chan4u on 8/29/2018.
 */

public class PostsDataFragment extends Fragment {


    private static final String TAG = "PostsDataFragment";
    String strSubReddit= "";
    ArrayList<DataModel> dataListNew;
    ListView listView;
    ProgressBar mProgressDialog;
    ConnectionDetector cd;
    SubredditDataAdapt adapter;
    List<DataModel> dataList = new ArrayList<>();
    public PostsDataFragment(){
    }
    @SuppressLint("ValidFragment")
    public PostsDataFragment(String categoryName) {
        this.strSubReddit = categoryName;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.postsfeed_fragment, container, false);
        cd = new ConnectionDetector(getActivity());
        listView = view.findViewById(R.id.listView);
        mProgressDialog = view.findViewById(R.id.feedProgressDialog);
        if(cd.isConnectingToInternet() == true) {
           mProgressDialog.setVisibility(View.VISIBLE);
            GetSubredditData();
        }else{
            mProgressDialog.setVisibility(View.GONE);
//           retreving the data from the roomdb and load in a list view
            dataList = RoomDb.getRoomDb(getContext()).dataListDao().getData();
            loaddata();
            Toast.makeText(getActivity(),"Check your Internet Connection",Toast.LENGTH_SHORT).show();
        }
        return view;
    }
    public void GetSubredditData()
    {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.getFeed(strSubReddit);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (!response.isSuccessful())
                    {
                        try {
                            Log.e("LOG", "Retrofit Error Response: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        if (response != null && response.body() != null)
                        {
                            JSONObject object = new JSONObject(response.body().string());
                            JSONObject dataObj = object.getJSONObject("data");
                            JSONArray chilbrenData = dataObj.getJSONArray("children");
                            dataListNew = DataModel.fromJson(chilbrenData);
                            if (dataListNew != null && !dataListNew.isEmpty()) {
                                if (dataList.size() > 0) {
                                    dataList.clear();
                                    if (adapter != null) {
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                                dataList.addAll(dataListNew);
                                RoomDb.getRoomDb(getContext()).dataListDao().clearData();
//                               // Inserting the data array list to the Room DB
                                RoomDb.getRoomDb(getContext()).dataListDao().insertList(dataList);
                                loaddata();
                                }

                                mProgressDialog.setVisibility(View.GONE);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mProgressDialog.setVisibility(View.GONE);
                Log.e(TAG, "----An Error Occured----->" + t.getMessage());
            }
        });
    }
    public void loaddata()
    {
        if(dataList!=null && dataList.size()>0){
            adapter = new SubredditDataAdapt(getActivity(),dataList);
            listView.setAdapter(adapter);
        }else{
            Toast.makeText(getActivity(),"No Data Found",Toast.LENGTH_SHORT).show();
        }
    }
}
