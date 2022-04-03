package kg.geektech.weatherapp;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;
import kg.geektech.weather.data.remote.RetrofitClient;
import kg.geektech.weather.data.remote.WeatherApi;
import kg.geektech.weather.data.repositories.MainRepositories;
import retrofit2.Retrofit;
@HiltAndroidApp
public class App extends Application {
//    private RetrofitClient retrofitClient;
//    private WeatherApi api;
//    public static MainRepositories repositories;
//
//    public void onCreate() {
//        super.onCreate();
//        retrofitClient = new RetrofitClient();
//        api = retrofitClient.provideApi();
//        repositories = new MainRepositories(api);
//    }
}

