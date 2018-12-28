package morefun.lxy.com.weather;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import morefun.lxy.com.weather.bean.City;
import morefun.lxy.com.weather.bean.Weather;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    List<City> cities = new ArrayList<>();
    List<Weather.Forecast> weathers = new ArrayList<>();
    EditText et;
    Button btn;
    private static final long TIMEOUT = 30L;
    MyAdapter adapter;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
//            JSONArray arr = new JSONArray();
            Type listType = new TypeToken<List<City>>() {
            }.getType();
            cities = new Gson().fromJson(getJson("_city.json"), listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        et = findViewById(R.id.city);
        btn = findViewById(R.id.search);
        list = findViewById(R.id.list);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = getCityCode(et.getText().toString().trim());
                if (TextUtils.isEmpty(code)) {
                    return;
                }
                Request request = new Request.Builder().url("http://t.weather.sojson.com/api/weather/city/" + code).get().build();
                OkHttpClient client = new OkHttpClient.Builder().readTimeout(TIMEOUT, TimeUnit.SECONDS).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "查询失败", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(final Call call, Response response) throws IOException {
                        String json = response.body().string();
                        Type aa = new TypeToken<List<Weather>>() {
                        }.getType();
                        Weather weather = new Gson().fromJson(json, Weather.class);
                        ArrayList<Weather.Forecast> arr = weather.getData().getForecast();
                        if (arr != null) {
                            weathers.clear();
                            weathers.addAll(arr);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }
                });
            }
        });


        adapter = new MyAdapter(this, weathers);
        list.setAdapter(adapter);
    }


    public String getJson(String fileName) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public String getCityCode(String name) {
        if (cities == null || cities.size() == 0 || TextUtils.isEmpty(name)) {
            Toast.makeText(this, "查无此城", Toast.LENGTH_LONG).show();
            return "";
        }
        for (City city : cities) {
            if (TextUtils.isEmpty(city.getCity_name())) {
                continue;
            }
            if (city.getCity_name().contains(name) || name.contains(city.getCity_name())) {
                return city.getCity_code();
            }
        }
        Toast.makeText(this, "查无此城", Toast.LENGTH_LONG).show();
        return "";
    }
}
