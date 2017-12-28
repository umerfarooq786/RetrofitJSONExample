package com.example.umerfarooq.retrofitjsonexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listViewArticles);
        getArticles();
    }
    private void getArticles()
    {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api api = retrofit.create(Api.class);
                Call<List<Articles>> call = api.getArticles();
                call.enqueue(new Callback<List<Articles>>() {
                    @Override
                    public void onResponse(Call<List<Articles>> call, Response<List<Articles>> response) {

                        List<Articles> articlelist = response.body();
                        String[] articles = new String[articlelist.size()];
                        for (int i = 0; i < articlelist.size(); i++) {
                            articles[i] = articlelist.get(i).getTitle();
                        }
                        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, articles));

                    }

                    @Override
                    public void onFailure(Call<List<Articles>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }




    }

