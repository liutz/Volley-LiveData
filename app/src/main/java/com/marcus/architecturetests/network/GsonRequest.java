package com.marcus.architecturetests.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.GsonBuilder;
import com.marcus.architecturetests.vo.Resource;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class GsonRequest<T> extends JsonRequest<T> {
    private final Class<T> clazz;

    public GsonRequest(int method, String url, Class<T> clazz, CustomData<T> customData) {
        super(method, url, null, customData, customData);
        this.clazz = clazz;
        customData.postValue(Resource.loading(null));
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return super.getParams();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new GsonBuilder().create().fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }
}