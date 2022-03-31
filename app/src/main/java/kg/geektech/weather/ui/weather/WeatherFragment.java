package kg.geektech.weather.ui.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;

import kg.geektech.weather.common.Resource;
import kg.geektech.weather.data.models.Main;
import kg.geektech.weather.data.models.MainResponse;
import kg.geektech.weather.data.models.Weather;
import kg.geektech.weatherapp.App;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.databinding.FragmentWeatherBinding;
import retrofit2.Call;


public class WeatherFragment extends Fragment {

    FragmentWeatherBinding binding;
    private WeatherViewModel viewModel;
    private Main main;
    private int temp;


    public WeatherFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getWeathers();
        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<MainResponse>>() {
            @Override
            public void onChanged(Resource<MainResponse> mainResponseResource) {
                switch (mainResponseResource.status) {
                    case SUCCESS: {
                        Weather weather = new Weather();
                        binding.textView.setText(String.valueOf(mainResponseResource.data.getMain().getTemp() + "\u2103"));
                        binding.textView18.setText(String.valueOf(mainResponseResource.data.getMain().getHumidity() + "%"));
                        binding.textView23.setText(String.valueOf(mainResponseResource.data.getMain().getPressure() + "mBar"));
                        binding.textView24.setText(String.valueOf(mainResponseResource.data.getWind().getSpeed()+"km/h"));
                        binding.textView3.setText(String.valueOf(mainResponseResource.data.getMain().getTempMin() + "\u2103"));
                        binding.textView4.setText(String.valueOf(mainResponseResource.data.getMain().getTempMax() + "\u2103"));
                        Glide.with(requireActivity()).load(weather.getIcon()).into(binding.sky);
                        binding.tvCityName.setText(String.valueOf(mainResponseResource.data.getSys().getCountry()));
                        setData();


                    }
                    case LOADING: {
                        break;
                    }
                    case ERROR: {
                        Snackbar.make(binding.getRoot(),
                                mainResponseResource.msg,
                                BaseTransientBottomBar.LENGTH_LONG)
                                .show();
                        break;
                    }
                }
            }
        });


    }

    private void setData() {
        Main main = new Main();
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        String currentDate = format.format(new Date());
        binding.date.setText(currentDate);
//        binding.textView3.setText(String.valueOf(main.getTempMax()+"\u2103"));
//        binding.textView4.setText(String.valueOf(main.getTempMin())+"\u2103");
//        binding.textView18.setText(String.valueOf(main.getHumidity())+"%");
//        binding.textView23.setText(String.valueOf(main.getPressure()+"mBar"));


    }
}