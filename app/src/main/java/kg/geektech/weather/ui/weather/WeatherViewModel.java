package kg.geektech.weather.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import kg.geektech.weatherapp.App;
import kg.geektech.weather.common.Resource;
import kg.geektech.weather.data.models.MainResponse;

public class WeatherViewModel extends ViewModel {

    public LiveData<Resource<MainResponse>> liveData;


    public void  getWeathers(){
        liveData = App.repositories.getWeathers();
    }

}
