/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ninja.lifecycle;

import com.google.inject.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class LifecycleSupportTest {

    @Before
    public void setUp() {
        MockSingletonService.started = 0;
        MockService.started = 0;
    }

    @Test public void serviceShouldBeStartedIfAccessedAfterLifecycleServiceIsStarted(){Injector injector=createInjector();start(injector);injector.getInstance(MockService.class);assertThat(MockService.started,equalTo(1));}

    private Injector createInjector(Module... modules) {
        List<Module> ms = new ArrayList<Module>(Arrays.asList(modules));
        ms.add(LifecycleSupport.getModule());
        return Guice.createInjector(ms);
    }

    private void start(Injector injector) {
        injector.getInstance(LifecycleService.class).start();
    }

    private void stop(Injector injector) {
        injector.getInstance(LifecycleService.class).stop();
    }

    @Singleton
    public static class MockSingletonService {
        static int started;
        static int disposed;
        @Start
        public void start() {
            started++;
        }
        @Dispose
        public void dispose() {
            disposed++;
        }
    }

    public static class MockService {
        static int started;
        static int disposed;
        @Start
        public void start() {
            started++;
        }
        @Dispose
        public void dispose() {
            disposed++;
        }
    }

}
