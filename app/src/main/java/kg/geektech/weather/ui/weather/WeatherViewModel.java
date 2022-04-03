package kg.geektech.weather.ui.weather;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weather.data.repositories.MainRepositories;
import kg.geektech.weatherapp.App;
import kg.geektech.weather.common.Resource;
import kg.geektech.weather.data.models.MainResponse;

@HiltViewModel
public class WeatherViewModel extends ViewModel {

    public LiveData<Resource<MainResponse>> liveData;
    private MainRepositories repositories;

    @Inject
    public WeatherViewModel(MainRepositories repositories) {
        this.repositories = repositories;
    }

    public void  getWeathers(String city){
        liveData = repositories.getWeathers(city);
    }

}
