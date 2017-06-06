package com.marcus.architecturetests.vo;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.volley.VolleyError;

import static com.marcus.architecturetests.vo.Status.ERROR;
import static com.marcus.architecturetests.vo.Status.LOADING;
import static com.marcus.architecturetests.vo.Status.SUCCESS;

/**
 * A generic class that holds a value with its loading status.
 *
 * @param <T>
 */
public class Resource<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final VolleyError errorMessage;

    @Nullable
    public final T data;

    public Resource(@NonNull Status status, @Nullable VolleyError errorMessage, @Nullable T data) {
        this.errorMessage = errorMessage;
        this.data = data;
        this.status = status;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, null, data);
    }

    public static <T> Resource<T> error(VolleyError errorMessage, @Nullable T data) {
        return new Resource<>(ERROR, errorMessage, data);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, null, data);
    }
}
