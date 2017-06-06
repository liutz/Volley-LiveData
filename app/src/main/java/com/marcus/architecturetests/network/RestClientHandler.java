package com.marcus.architecturetests.network;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.marcus.architecturetests.vo.PeopleRequest;

import javax.inject.Inject;

public class RestClientHandler {

    private static final String TAG = "RestClientHandler";

    private RequestQueue requestQueue;

    @Inject
    public RestClientHandler(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public CustomData<PeopleRequest> findPeople() {
        final String url = "http://swapi.co/api/people/";
        CustomData<PeopleRequest> liveData = new CustomData<>();

        GsonRequest<PeopleRequest> request = new GsonRequest<>(Request.Method.GET, url, PeopleRequest.class, liveData);
        addToRequestQueue(request);
        return liveData;
    }

    private <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        requestQueue.add(req);
    }

    private <T> void addToRequestQueue(Request<T> req) {
        addToRequestQueue(req, TAG);
    }

    public void cancelPendingRequests(String tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
