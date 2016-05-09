package com.tutu.pestcs.RxBus;


import android.util.Log;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * Created by nana on 2015/11/5 11:49.
 */
public final class RxBus {
    private static final String TAG = RxBus.class.getSimpleName();
    private static RxBus instance;
    private static final String suffix = "_sticky";
    private static ConcurrentHashMap<String, Subject> subjectMapper = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Object> eventStickyMapper = new ConcurrentHashMap<>();

    private RxBus() {
    }

    public static <T> Observable<T> obtainEvent(Class<T> clazz) {
        Subject<T, T> subject = subjectMapper.get(clazz.getName());
        if (subject == null) {
            subject = PublishSubject.create();
            subjectMapper.put(clazz.getName(), subject);
        }
        return subject;
    }

    public static <T> Observable<T> obtainStickyEvent(Class<T> clazz) {
        Subject subject = subjectMapper.get(clazz.getName() + suffix);
        if (subject == null) {
            subject = BehaviorSubject.create();
            subjectMapper.put(clazz.getName() + suffix, subject);
        }
        return subject;
    }

    public static <T> T obtainStickyObject(Class<T> clazz) {
        Object o = eventStickyMapper.get(clazz.getName());
        if (o == null) {
            try {
                o = clazz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(clazz.getName() + "--没有默认的构造函数");
            }
            eventStickyMapper.put(clazz.getName(), new SoftReference<Object>(o));
        }
        return (T) o;
    }

    public static void recycleEvent(Class<?> clazz) {
        subjectMapper.remove(clazz.getName());
    }

    public static void recycleStickyEvent(Class<?> clazz) {
        if (subjectMapper.remove(clazz.getName() + suffix) != null) {
            eventStickyMapper.remove(clazz.getName() + suffix);
        }
    }

    public static <T> void postEvent(T o, Class<? super T> clazz) {
        Subject subject = subjectMapper.get(clazz.getName());
        if (subject != null) {
            Log.i("rx", "onnext");
            subject.onNext(o);
        }
    }

    public static <T> void postStickyEvent(T o, Class<? super T> clazz) {
        String name = clazz.getName();
        eventStickyMapper.put(name, o);
        Subject subject = subjectMapper.get(name + suffix);
        if (subject == null) {
            subject = BehaviorSubject.create();
            subjectMapper.put(clazz.getName() + suffix, subject);
        }
        subject.onNext(o);
    }
}


