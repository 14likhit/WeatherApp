package com.uniqolabel.weatherapp.base;

public abstract class BasePresenter<T extends BaseView> {

    private T view;

    public T getView() {
        return view;
    }

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

}
