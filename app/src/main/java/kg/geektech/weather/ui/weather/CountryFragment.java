package kg.geektech.weather.ui.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weather.base.BaseFragment;
import kg.geektech.weather.common.OnClick;
import kg.geektech.weather.common.Resource;
import kg.geektech.weather.data.models.MainResponse;
import kg.geektech.weather.data.models.Sys;
import kg.geektech.weather.data.remote.RetrofitClient;
import kg.geektech.weather.data.remote.WeatherApi;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.databinding.FragmentCountryBinding;
import retrofit2.Call;

@AndroidEntryPoint
public class CountryFragment extends BaseFragment<FragmentCountryBinding> {


    @Override
    protected FragmentCountryBinding bind() {
        return FragmentCountryBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void setUpViews() {

    }

    @Override
    protected void setUpListeners() {
      binding.btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String city = binding.etCity.getText().toString();
              controller.navigate(CountryFragmentDirections.actionCountryFragmentToWeatherFragment().setCity(city));
          }
      });

    }

    @Override
    protected void setUpObservers() {
//        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<Sys>>() {
//            @Override
//            public void onChanged(Resource<Sys> sysResource) {
//                binding.etCity.setText(sysResource.data.getCountry());
//                binding.btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                    }
//                });
//            }
//        });
    }

    @Override
    protected void callRequests() {


    }


}

