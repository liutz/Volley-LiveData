package com.marcus.architecturetests.di;

import com.marcus.architecturetests.ui.activity.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = {})
    abstract MainActivity contributeMainActivity();
}
