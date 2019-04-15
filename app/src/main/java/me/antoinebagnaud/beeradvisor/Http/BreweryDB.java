package me.antoinebagnaud.beeradvisor.Http;

import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor;

import java.util.List;

import me.antoinebagnaud.beeradvisor.BuildConfig;
import me.antoinebagnaud.beeradvisor.Model.Beer;
import me.antoinebagnaud.beeradvisor.Model.Beers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class BreweryDB {

    private Retrofit retrofit;

    private BreweryService service = null;

    public BreweryDB() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new OkHttpProfilerInterceptor());
        }
        OkHttpClient client = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://sandbox-api.brewerydb.com/v2/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public BreweryService getService() {
        if (service == null) {
            service = retrofit.create(BreweryService.class);
        }
        return service;
    }

    public interface BreweryService {
        @GET("search/?key=d9f403712d3b9f1f8da51bc4defa3dfa")
        Call<Beers> searchBeer(@Query("q") String q);
    }

}
