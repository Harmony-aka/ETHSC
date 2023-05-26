package com.example.ethsc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ETHstatService etHstatService = retrofit.create(ETHstatService.class);
        Call<ETHinfo> call = etHstatService.getStat("stats", "ethprice", Utils.API_KEY);
        call.enqueue(new ResponseSpace());
    }

    class ResponseSpace implements Callback<ETHinfo> {

        @Override
        public void onResponse(Call<ETHinfo> call, Response<ETHinfo> response) {
            if(response.isSuccessful() && response.code() == 200){
                TextView price_text = findViewById(R.id.price);
                ETHinfo etHinfo = response.body();
                price_text.setText(etHinfo.status);
                TextView ethbtc_text = findViewById(R.id.ethbtc);
            }
        }

        @Override
        public void onFailure(Call<ETHinfo> call, Throwable t) {
            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}