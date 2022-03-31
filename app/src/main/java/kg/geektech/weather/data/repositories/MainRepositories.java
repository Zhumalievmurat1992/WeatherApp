package kg.geektech.weather.data.repositories;

import androidx.lifecycle.MutableLiveData;

import kg.geektech.weather.common.Resource;
import kg.geektech.weather.data.models.MainResponse;
import kg.geektech.weather.data.remote.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepositories {

    private WeatherApi api;

    public MainRepositories(WeatherApi api) {
        this.api = api;
    }

    public MutableLiveData<Resource<MainResponse>> getWeathers(){
        MutableLiveData<Resource<MainResponse>> liveData = new MutableLiveData<>();
          liveData.setValue(Resource.loading());
          api.getWeathers("Bishkek","007d33343df7413b825b5c04f4ef9214","metric")
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
}
