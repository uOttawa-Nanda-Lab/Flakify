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

package ninja;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.inject.Injector;

@RunWith(MockitoJUnitRunner.class)
public class RouteBuilderImplTest {

    @Mock
    Injector injector;

    @Test public void testBasicPlaceholersAndParameters(){RouteBuilderImpl routeBuilder=new RouteBuilderImpl();routeBuilder.GET().route("/{name}/dashboard");Route route=buildRoute(routeBuilder);assertFalse(route.matches("GET","/dashboard"));assertTrue(route.matches("GET","/John/dashboard"));Map<String, String> map=route.getPathParametersEncoded("/John/dashboard");assertEquals(1,map.entrySet().size());assertEquals("John",map.get("name"));routeBuilder=new RouteBuilderImpl();routeBuilder.GET().route("/{name}/{id}/dashboard");route=buildRoute(routeBuilder);assertFalse(route.matches("GET","/dashboard"));assertTrue(route.matches("GET","/John/20/dashboard"));map=route.getPathParametersEncoded("/John/20/dashboard");assertEquals(2,map.entrySet().size());assertEquals("John",map.get("name"));assertEquals("20",map.get("id"));}

    private Route buildRoute(RouteBuilderImpl builder) {
        builder.with(MockController.class, "execute");
        return builder.buildRoute(injector);
    }

    public static class MockController {
        public Result execute() {
            return null;
        }
    }

}
