package com.marcus.architecturetests.network;

import android.arch.lifecycle.MutableLiveData;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.marcus.architecturetests.vo.Resource;

public class CustomData<T> extends MutableLiveData<Resource<T>> implements Response.Listener<T>,
        Response.ErrorListener {

    @Override
    public void onErrorResponse(VolleyError error) {
        postValue(Resource.error(error, null));
    }

    @Override
    public void onResponse(T response) {
        postValue(Resource.success(response));
    }
}

