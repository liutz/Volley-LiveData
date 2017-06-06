package com.marcus.architecturetests.di;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.marcus.architecturetests.network.RestClientHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    RequestQueue provideRequestQueue(Application app) {
        return Volley.newRequestQueue(app);
    }

    @Singleton
    @Provides
    RestClientHandler provideRestClientHandler(RequestQueue requestQueue) {
        return new RestClientHandler(requestQueue);
    }
}
