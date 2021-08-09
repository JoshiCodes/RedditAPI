package de.joshizockt.redditapi;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class RestAction<A> {

    private Callable<A> context;

    public RestAction() {
    }

    public RestAction<A> setContext(Callable<A> context) {
        this.context = context;
        return this;
    }

    public void queue() {
        queue(null, null);
    }

    public void queue(Consumer<A> onFinish) {
        queue(onFinish, null);
    }

    public void queue(Consumer<A> onFinish, Consumer<Exception> onError) {
        A a;
        try {
            a = context.call();
        } catch (Exception e) {
            if(onError != null) {
                onError.accept(e);
            } else e.printStackTrace();
            return;
        }
        if(onFinish != null)
            onFinish.accept(a);
    }

    public A complete(Consumer<Exception> onError) {
        A a;
        try {
            a = context.call();
        } catch (Exception e) {
            if(onError != null) {
                onError.accept(e);
            } else e.printStackTrace();
            return null;
        }
        return a;
    }

    public A complete() throws Exception {
        return context.call();
    }

}
