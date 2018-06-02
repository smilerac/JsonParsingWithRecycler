package com.kthdvs.day2;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    //progressdialog used to show that round loading thingy before data is loaaded
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycleView);

        //progressDialog
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("loading . dot. dot...");
        progressDialog.setCancelable(false);//cannot be cancelled until fully loaded
        progressDialog.show();//must show

        getMenuJson();
    }

    public void getMenuJson(){

        RetrofitApiInterface retrofitApiInterface= RetrofitClient.getRetrofit().create(RetrofitApiInterface.class);
        Call<List<MenuModel>>modelClassCall =  retrofitApiInterface.getMenu();

        modelClassCall.enqueue(new Callback<List<MenuModel>>() {
            @Override
            public void onResponse(Call<List<MenuModel>> call, Response<List<MenuModel>> response) {
               //1st method to call constructor
                List<MenuModel> models = response.body();
                RecyclerviewAdapter recyclerviewAdapter=new RecyclerviewAdapter(MainActivity.this,models);
                //2nd method i.e without calling
                //RecyclerviewAdapter recyclerviewAdapter1=new RecyclerviewAdapter(MainActivity.this,response.body());

                //to fix view i.e either list or grid, in this case list so use LinearLayoutManager
                //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

                //for grid view
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(MainActivity.this,2);

                //for horizontal
                //LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                //recyclerView.setLayoutManager(horizontalLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(layoutManager);//not required for horizontal
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerviewAdapter); // calling is a must while using 1st method
                recyclerviewAdapter.notifyDataSetChanged(); // no need to refresh page
                //if data is showing, dismis the loading sign
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<MenuModel>> call, Throwable t) {

            }
        });
    }
}
