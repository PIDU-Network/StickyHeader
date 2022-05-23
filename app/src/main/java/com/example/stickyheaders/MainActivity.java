package com.example.stickyheaders;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.gson.JsonObject;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerMain;
    LinearLayoutManager linearLayoutManager;
    private ParentItemAdapter parentItemAdapter;
    private ArrayList<Model> list = new ArrayList<>();

    // creating a variable for our page and limit as 2
    // as our api is having highest limit as 2 so
    // we are setting a limit = 2
    int page = 0, limit = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerMain = findViewById(R.id.rv);


        linearLayoutManager = new LinearLayoutManager(this);
        recyclerMain.setLayoutManager(linearLayoutManager);

        getrecycleview();

        RecyclerItemDecoration recyclerItemDecoration = new RecyclerItemDecoration(this, getResources().
                getDimensionPixelSize(R.dimen.header_height),
                true, getSectionCallback(list));
        recyclerMain.addItemDecoration(recyclerItemDecoration);
    }

    private RecyclerItemDecoration.SectionCallback getSectionCallback(final ArrayList<Model> list) {
        return new RecyclerItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int pos) {
                Log.e("issection" + pos, "");
                return pos == 0 || list.get(pos).getHeader() != list.get(pos - 1).getHeader();
            }

            @Override
            public String getSectionHeaderName(int pos) {
                Model arrayList = list.get(pos);
                Log.e("getsectionheadername" + arrayList.getHeader(), "");
                return arrayList.getHeader();
            }

        };
    }

    private void getrecycleview() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.pidu.in/mybiz/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiPlaceHolder retrofitAPI = retrofit.create(ApiPlaceHolder.class);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("apikey", "164310030161efb88d2d888");
        jsonObject.addProperty("userid", "1");
        jsonObject.addProperty("storeid", "158");
        jsonObject.addProperty("page", "2");
        Call<ResponseBody> call = retrofitAPI.menulistallitem(jsonObject);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        try {
                            JSONObject json = new JSONObject(response.body().string());
                            if (json.getString("status").equals("success")) {
                                JSONObject json1 = new JSONObject(json.getString("listdata"));
                                Iterator x = json1.keys();
                                list.clear();
                                while (x.hasNext()) {
                                    String key = (String) x.next();
                                    JSONObject json2 = new JSONObject(json1.get(key).toString());
                                    JSONObject json3 = new JSONObject(json2.getString("data"));
                                    if (json3.toString().equals("{}")) {

                                    } else {
                                        Iterator y = json3.keys();
                                        while (y.hasNext()) {
                                            String key1 = (String) y.next();
                                            Log.e("key" + key1, "");

                                        }

                                        list.add(new Model(
                                                json2.getString("categoryid") + "",
                                                json2.getString("header") + "",
                                                json2.getString("description") + "",
                                                json2.getString("layout") + ""));


                                    }
                                }
                                parentItemAdapter = new ParentItemAdapter(list, MainActivity.this);
                                recyclerMain.setAdapter(parentItemAdapter);

                                Log.e("MENUFRAG", "0");


                            } else {


                            }


                        } catch (IOException | JSONException e) {
                            Toast.makeText(MainActivity.this, "Catch Error", Toast.LENGTH_SHORT).show();


                        }
                    }
                } else {

                    Toast.makeText(MainActivity.this, "Fail response", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();


            }
        });

    }

}


