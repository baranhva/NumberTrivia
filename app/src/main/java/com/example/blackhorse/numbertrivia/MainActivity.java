package com.example.blackhorse.numbertrivia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v7.widget.RecyclerView.HORIZONTAL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<TriviaItem> trivias;
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recyclerViewTrivia);
        trivias = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TriviaAdapter(trivias);
        mRecyclerView.setAdapter(mAdapter);

//        DividerItemDecoration itemDecor = new DividerItemDecoration(this, HORIZONTAL);
//        mRecyclerView.addItemDecoration(itemDecor);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestData(); }
        });

    }

    private void requestData() {
        ApiService service = ApiService.retrofit.create(ApiService.class);
        retrofit2.Call<TriviaItem> call = service.getRandomQuoteAndNumber();
        call.enqueue(new Callback<TriviaItem>() {
            @Override
            public void onResponse(Call<TriviaItem> call, Response<TriviaItem> response) {
                TriviaItem dayQuoteItem = response.body();
                Log.i(TAG,"Number: "+dayQuoteItem.getNumber()+"  Quote:  "+dayQuoteItem.getText());
                trivias.add(0,new TriviaItem(dayQuoteItem.getText(), dayQuoteItem.getNumber()));
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TriviaItem> call, Throwable t) {
                Log.i(TAG, "Something went wrong: " + t.getMessage());
            }

        });
    }


}
