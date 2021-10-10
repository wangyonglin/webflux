package com.wangyonglin.webflux.library;


public interface Callback<T> {
    default void resolve(T res){};
    default void reject(Exception e){};
}