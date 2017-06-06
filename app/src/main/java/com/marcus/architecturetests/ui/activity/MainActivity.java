package com.marcus.architecturetests.ui.activity;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.marcus.architecturetests.R;
import com.marcus.architecturetests.databinding.ActivityMainBinding;
import com.marcus.architecturetests.network.RestClientHandler;
import com.marcus.architecturetests.ui.adapter.PeopleAdapter;
import com.marcus.architecturetests.vo.People;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    @Inject
    RestClientHandler restClientHandler;

    private LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        loadPeople();
    }

    private void loadPeople() {
        restClientHandler.findPeople().observe(this, peopleResource -> {
            if (peopleResource != null) {
                switch (peopleResource.status) {
                    case LOADING:
                        binding.progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        binding.progressBar.setVisibility(View.GONE);
                        if (peopleResource.data != null) {
                            loadAdapter(peopleResource.data.getResults());
                        } else {
                            Snackbar
                                    .make(binding.getRoot(), R.string.no_data_found, Snackbar.LENGTH_INDEFINITE)
                                    .setAction(R.string.retry, view -> loadPeople())
                                    .show();
                        }
                        break;
                    case ERROR:
                        binding.progressBar.setVisibility(View.GONE);
                        Snackbar
                                .make(binding.getRoot(), R.string.error, Snackbar.LENGTH_INDEFINITE)
                                .setAction(R.string.retry, view -> loadPeople())
                                .show();
                        break;
                }
            }
        });
    }

    private void loadAdapter(List<People> peopleList) {
        binding.peopleList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.peopleList.setAdapter(new PeopleAdapter(peopleList));
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }
}
