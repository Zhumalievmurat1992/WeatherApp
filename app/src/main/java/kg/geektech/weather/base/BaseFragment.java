package kg.geektech.weather.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import kg.geektech.weatherapp.R;

public  abstract class BaseFragment<VB extends ViewBinding> extends Fragment  {
    // region values
    protected VB binding;
    protected  abstract  VB bind();
    protected  NavController controller;
    //endregion


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = bind();
        controller = Navigation.findNavController(requireActivity(),R.id.nav_host);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews();
        callRequests();
        setUpListeners();
        setUpObservers();
    }

    protected  abstract  void setUpViews();
    protected abstract void setUpListeners();
    protected abstract void setUpObservers();
    protected abstract void callRequests();
}
