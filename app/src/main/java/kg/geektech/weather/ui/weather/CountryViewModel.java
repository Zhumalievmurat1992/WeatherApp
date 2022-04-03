package kg.geektech.weather.ui.weather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import kg.geektech.weather.common.Resource;
import kg.geektech.weather.data.models.Sys;
import kg.geektech.weather.data.repositories.MainRepositories;
@HiltViewModel
public class CountryViewModel extends ViewModel {

    private MainRepositories repositories;
    public LiveData<Resource<Sys>> liveData;
    @Inject
    public CountryViewModel(MainRepositories repositories) {
        this.repositories = repositories;
    }

    public void getWeatherById(String id){

       // liveData = repositories.getWeatherByCity();
    }
}
