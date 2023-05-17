package com.example.android.myweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.myweatherapp.databinding.ActivityMainBinding;
import com.example.android.myweatherapp.model.MyWeather;

public class MainActivity extends AppCompatActivity implements MyWeatherTaskListener
{
    private ActivityMainBinding binding;

    //Web URL of the JSON file
    private String mApiKey = "c9a1e8fde73a399c4c08b3d29a246b73";
    private String mCity = "Chicago";
    private String mCountry = "United States";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //http://api.openweathermap.org/data/2.5/weather?q=city,country&APPID={your api key};
        String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=" + mCity + "," + mCountry + "&APPID=" + mApiKey;
        new MyWeatherTask(this).execute(weatherURL);
    }

    @Override
    public void onMyWeatherTaskPreExecute()
    {
        binding.myLoadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMyWeatherTaskPostExecute(MyWeather myWeather)
    {
        if (myWeather != null)
        {
            binding.cityTextView.setText(mCity);
            binding.countryTextView.setText(mCountry);

            binding.weatherConditionTextView.setText(myWeather.getWeatherCondition());
            binding.weatherDescriptionTextView.setText(myWeather.getWeatherDescription());

            float tempCelsius = myWeather.getTemperature() - 273.15f;
            float tempFahrenheit = (tempCelsius * 9/5) + 32;

// Round the temperature to the nearest integer
            int tempFahrenheitRounded = Math.round(tempFahrenheit);

            String tempStr = String.valueOf(tempFahrenheitRounded);
            binding.temperatureTextView.setText(tempStr + "°F");


            String imgUrl = "https://openweathermap.org/img/wn/" + myWeather.getWeatherIconStr() + "@2x.png";

            Glide.with(MainActivity.this)
                    .asBitmap()
                    .load(imgUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(binding.weatherIconImageView);
        }
        binding.myLoadingLayout.setVisibility(View.GONE);
    }
}