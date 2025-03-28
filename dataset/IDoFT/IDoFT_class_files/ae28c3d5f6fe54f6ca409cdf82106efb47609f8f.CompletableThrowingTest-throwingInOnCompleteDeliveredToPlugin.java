/*
 * Copyright (C) 2016 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package retrofit2.adapter.rxjava;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

import static org.assertj.core.api.Assertions.assertThat;

public final class CompletableThrowingTest {
  @Rule public final MockWebServer server = new MockWebServer();
  @Rule public final TestRule resetRule = new RxJavaPluginsResetRule();
  @Rule public final RecordingSubscriber.Rule subscriberRule = new RecordingSubscriber.Rule();

  interface Service {
    @GET("/") Completable completable();
  }

  private Service service;

  @Before public void setUp() {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(server.url("/"))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
    service = retrofit.create(Service.class);
  }

  @Test public void throwingInOnCompleteDeliveredToPlugin() {
    server.enqueue(new MockResponse());

    final AtomicReference<Throwable> pluginRef = new AtomicReference<>();
    RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
      @Override public void handleError(Throwable throwable) {
        if (!pluginRef.compareAndSet(null, throwable)) {
          throw Exceptions.propagate(throwable); // Don't swallow secondary errors!
        }
      }
    });

    RecordingSubscriber<Void> observer = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    service.completable().unsafeSubscribe(new ForwardingCompletableObserver(observer) {
      @Override public void onCompleted() {
        throw e;
      }
    });

    assertThat(pluginRef.get()).isSameAs(e);
  }

  @Test public void bodyThrowingInOnErrorDeliveredToPlugin() {
    server.enqueue(new MockResponse().setResponseCode(404));

    final AtomicReference<Throwable> pluginRef = new AtomicReference<>();
    RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
      @Override public void handleError(Throwable throwable) {
        if (!pluginRef.compareAndSet(null, throwable)) {
          throw Exceptions.propagate(throwable); // Don't swallow secondary errors!
        }
      }
    });

    RecordingSubscriber<Void> observer = subscriberRule.create();
    final RuntimeException e = new RuntimeException();
    final AtomicReference<Throwable> errorRef = new AtomicReference<>();
    service.completable().unsafeSubscribe(new ForwardingCompletableObserver(observer) {
      @Override public void onError(Throwable throwable) {
        errorRef.set(throwable);
        throw e;
      }
    });

    CompositeException composite = (CompositeException) pluginRef.get();
    assertThat(composite.getExceptions()).containsExactly(errorRef.get(), e);
  }

  static abstract class ForwardingCompletableObserver implements CompletableSubscriber {
    private final RecordingSubscriber<Void> delegate;

    ForwardingCompletableObserver(RecordingSubscriber<Void> delegate) {
      this.delegate = delegate;
    }

    @Override public void onSubscribe(Subscription d) {
    }

    @Override public void onCompleted() {
      delegate.onCompleted();
    }

    @Override public void onError(Throwable throwable) {
      delegate.onError(throwable);
    }
  }
}
