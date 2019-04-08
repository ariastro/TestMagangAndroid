package com.astronout.testmagangdot;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.astronout.testmagangdot.adapter.MalangAdapter;
import com.astronout.testmagangdot.api.Client;
import com.astronout.testmagangdot.api.Service;
import com.astronout.testmagangdot.model.Malang;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.main_view)
    SwipeRefreshLayout swipeRefreshLayout;

    MalangAdapter adapter;
    List<Malang> malangList;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initViews();
            }
        });

    }

    private void initViews(){
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Data...");
        pd.setCancelable(false);
        pd.show();

        malangList = new ArrayList<>();
        adapter = new MalangAdapter(this, malangList);
        recyclerView.setAdapter(adapter);

        if (MainActivity.this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        }

        loadJSON();

    }

    private void loadJSON(){
        try {
            Client client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<List<Malang>> call = apiService.getMalang();
            call.enqueue(new Callback<List<Malang>>() {
                @Override
                public void onResponse(Call<List<Malang>> call, Response<List<Malang>> response) {
                    List<Malang> malangs = response.body();
                    recyclerView.setAdapter(new MalangAdapter(MainActivity.this, malangs));

                    for (Malang m : malangs){
                        Log.d("Image", m.getmImage());
                        Log.d("Caption", m.getmCaption());
                        Log.d("Size", String.valueOf(malangs.size()));
                    }

                    if (swipeRefreshLayout.isRefreshing()){
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    pd.dismiss();

                }

                @Override
                public void onFailure(Call<List<Malang>> call, Throwable t) {
                    Log.d("Error : ", t.getMessage());
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(this, "Aw Snap! Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

}
