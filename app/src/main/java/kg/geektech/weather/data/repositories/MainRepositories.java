package kg.geektech.weather.data.repositories;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import kg.geektech.weather.common.Resource;
import kg.geektech.weather.data.models.MainResponse;
import kg.geektech.weather.data.models.Sys;
import kg.geektech.weather.data.remote.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepositories {

    private WeatherApi api;
    @Inject
    public MainRepositories(WeatherApi api) {
        this.api = api;
    }

    public MutableLiveData<Resource<MainResponse>> getWeathers(String city){
        MutableLiveData<Resource<MainResponse>> liveData = new MutableLiveData<>();
          liveData.setValue(Resource.loading());
          api.getWeathers(city,"007d33343df7413b825b5c04f4ef9214","metric")
                  .enqueue(new Callback<MainResponse>() {
              @Override
              public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                 if (response.isSuccessful() && response.body() != null){
                     liveData.setValue(Resource.success(response.body()));
                 }else {
                     liveData.setValue(Resource.error(response.message(),null));
                 }
              }

              @Override
              public void onFailure(Call<MainResponse> call, Throwable t) {
                     liveData.setValue(Resource.error(t.getLocalizedMessage(),null));
              }
          });
          return liveData;
    }

//    public MutableLiveData<Resource<Sys>> getWeatherByCity(){
//        MutableLiveData<Resource<Sys>> liveData = new MutableLiveData<>();
//        liveData.setValue(Resource.loading());
//        api.getWeatherByCity("Bishkek","007d33343df7413b825b5c04f4ef9214")
//                .enqueue(new Callback<Sys>() {
//                    @Override
//                    public void onResponse(Call<Sys> call, Response<Sys> response) {
//                        if (response.isSuccessful() && response.body() != null){
//                            liveData.setValue(Resource.success(response.body()));
//                        }else {
//                            liveData.setValue(Resource.error(response.message(),null));
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Sys> call, Throwable t) {
//                        liveData.setValue(Resource.error(t.getLocalizedMessage(),null));
//                    }
//                });
//        return liveData;
//    }
}
