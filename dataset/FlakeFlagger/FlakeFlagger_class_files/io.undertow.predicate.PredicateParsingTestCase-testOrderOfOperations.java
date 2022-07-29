/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2013 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
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

package io.undertow.predicate;

import java.util.HashMap;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Stuart Douglas
 */
public class PredicateParsingTestCase {

    @Test public void testOrderOfOperations(){expect("exists[%{i,Content-Length}] or exists[value=%{i,Trailer}] and exists[%{i,Other}]",false,true);expect("(exists[%{i,Content-Length}] or exists[value=%{i,Trailer}]) and exists[%{i,Other}]",false,false);}

    private void expect(String string, boolean result1, boolean result2) {
        try {
            Predicate predicate = PredicateParser.parse(string, PredicateParsingTestCase.class.getClassLoader());
            HttpServerExchange e = new HttpServerExchange(null);
            e.getRequestHeaders().add(Headers.TRAILER, "a");
            Assert.assertEquals(result1, predicate.resolve(e));
            e.getRequestHeaders().add(Headers.CONTENT_LENGTH, "a");
            Assert.assertEquals(result2, predicate.resolve(e));
        } catch (Throwable ex) {
            throw new RuntimeException("String " + string, ex);
        }
    }
}
