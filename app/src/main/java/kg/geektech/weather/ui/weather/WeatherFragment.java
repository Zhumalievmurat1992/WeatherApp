package kg.geektech.weather.ui.weather;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import kg.geektech.weather.base.BaseFragment;
import kg.geektech.weather.common.OnClick;
import kg.geektech.weather.common.Resource;
import kg.geektech.weather.data.models.Main;
import kg.geektech.weather.data.models.MainResponse;
import kg.geektech.weather.data.models.Sys;
import kg.geektech.weather.data.models.Weather;
import kg.geektech.weatherapp.App;
import kg.geektech.weatherapp.R;
import kg.geektech.weatherapp.databinding.FragmentWeatherBinding;
import retrofit2.Call;

@AndroidEntryPoint
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {

    private WeatherViewModel viewModel;
    private WeatherFragmentArgs args;
//    private OnClick<String> onClick;
//    public void setOnClick(OnClick<String> onClick) {
//        this.onClick = onClick;
//    }

    public WeatherFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            args = WeatherFragmentArgs.fromBundle(getArguments());
        }
        // viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
    }

    @Override
    protected FragmentWeatherBinding bind() {
        return FragmentWeatherBinding.inflate(getLayoutInflater());
    }


    @Override
    protected void setUpViews() {
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
    }

    @Override
    protected void setUpListeners() {
        binding.tvCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.countryFragment);
            }
        });
    }

    @Override
    protected void setUpObservers() {

        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<MainResponse>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Resource<MainResponse> mainResponseResource) {


                switch (mainResponseResource.status) {


                    case SUCCESS: {
                        loadData(mainResponseResource.data);
                        binding.textView.setText(String.valueOf(mainResponseResource.data.getMain().getTemp()));

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

    @Override
    protected void callRequests() {
        viewModel.getWeathers(args.getCity());
    }

    private void loadData(MainResponse response) {

       //binding.textView.setText(String.valueOf(response.getMain().getTemp()+""));
        binding.textView18.setText(response.getMain().getHumidity() + "%");
        binding.textView23.setText(response.getMain().getPressure() + "mBar");
        binding.textView24.setText(response.getWind().getSpeed() + "km/h");
        binding.textView3.setText(response.getMain().getTempMin() + "\u2103");
        binding.textView4.setText(response.getMain().getTempMax() + "\u2103");
        binding.tvCityName.setText(String.valueOf(response.getName()));
        binding.textView21.setText(getDate(response.getSys().getSunrise(), "hh:mm a"));
        binding.textView28.setText(getDate(response.getSys().getSunset(), "hh:mm a"));
        binding.textView27.setText(getDate(response.getDt(), "hh:mm"));
        binding.textView2.setText(String.valueOf(response.getWeather().get(0).getDescription()));

        String urlImg = "http://openweathermap.org/img/wn/"
                + response.getWeather().get(0).getIcon() + ".png";
        Glide.with(requireActivity()).load(urlImg).into(binding.sky);
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy  HH:mm");
        String currentDate = format.format(new Date());
        binding.date.setText(currentDate);

    }

    public static String getDate(Integer miliS, String data) {

        SimpleDateFormat format = new SimpleDateFormat(data);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(miliS);
        return format.format(calendar.getTime());

    }


}