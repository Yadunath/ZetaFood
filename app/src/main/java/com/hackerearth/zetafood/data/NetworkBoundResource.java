package com.hackerearth.zetafood.data;


import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class NetworkBoundResource<ResultType, RequestType> {
    String TAG="NetworkBoundResource";
    private Observable<Resource<ResultType>> result;

    @MainThread
    protected NetworkBoundResource() {
        Observable<Resource<ResultType>> source;
        if (shouldFetch()) {
            source = createCall()
                    .subscribeOn(Schedulers.io())
                    .doOnNext(apiResponse -> saveCallResult(processResponse(apiResponse)))
                    .flatMap(apiResponse -> loadFromDb().toObservable().map(Resource::success))
                    .doOnError(t -> onFetchFailed())
                    .onErrorResumeNext(t -> {
                        return null;
                    })
                    .observeOn(AndroidSchedulers.mainThread());

        } else {
            source = loadFromDb()
                    .toObservable()
                    .map(Resource::success);
        }

        result = Observable.concat(
                loadFromDb()
                        .toObservable()
                        .map(Resource::loading)
                        .take(1),
                source
        );
    }

    public Observable<Resource<ResultType>> getAsObservable() {
            Log.d(TAG,"result send");
        return result;}

    protected void onFetchFailed() {}

    @WorkerThread
    protected RequestType processResponse(Resource<RequestType> response) {return response.data;}

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @MainThread
    protected abstract boolean shouldFetch();

    @NonNull
    @MainThread
    protected abstract Flowable<ResultType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract Observable<Resource<RequestType>> createCall();
}