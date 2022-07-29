/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012 Red Hat, Inc., and individual contributors
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

package io.undertow.server.protocol.http;

import java.nio.ByteBuffer;

import io.undertow.UndertowOptions;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.HttpString;
import io.undertow.util.Methods;
import io.undertow.util.Protocols;
import org.junit.Assert;
import org.junit.Test;
import org.xnio.OptionMap;

/**
 * Basic test of the HTTP parser functionality.
 * <p/>
 * This tests parsing the same basic request, over and over, with minor differences.
 * <p/>
 * Not all these actually conform to the HTTP/1.1 specification, however we are supposed to be
 * liberal in what we accept.
 *
 * @author Stuart Douglas
 */
public class SimpleParserTestCase {


    @Test public void testPathParameters(){byte[] in="GET /somepath;p1 HTTP/1.1\r\n\r\n".getBytes();ParseState context=new ParseState();HttpServerExchange result=new HttpServerExchange(null);HttpRequestParser.instance(OptionMap.create(UndertowOptions.ALLOW_ENCODED_SLASH,true)).handle(ByteBuffer.wrap(in),context,result);Assert.assertSame(Methods.GET,result.getRequestMethod());Assert.assertEquals("/somepath",result.getRequestPath());Assert.assertEquals("/somepath;p1",result.getRequestURI());Assert.assertTrue(result.getPathParameters().containsKey("p1"));in="GET /somepath;p1=v1&p2=v2?q1=v3 HTTP/1.1\r\n\r\n".getBytes();context=new ParseState();result=new HttpServerExchange(null);HttpRequestParser.instance(OptionMap.create(UndertowOptions.ALLOW_ENCODED_SLASH,true)).handle(ByteBuffer.wrap(in),context,result);Assert.assertSame(Methods.GET,result.getRequestMethod());Assert.assertEquals("/somepath",result.getRequestPath());Assert.assertEquals("/somepath;p1=v1&p2=v2",result.getRequestURI());Assert.assertEquals("q1=v3",result.getQueryString());Assert.assertEquals("v1",result.getPathParameters().get("p1").getFirst());Assert.assertEquals("v2",result.getPathParameters().get("p2").getFirst());Assert.assertEquals("v3",result.getQueryParameters().get("q1").getFirst());}


    private void runTest(final byte[] in) {
        final ParseState context = new ParseState();
        HttpServerExchange result = new HttpServerExchange(null);
        HttpRequestParser.instance(OptionMap.EMPTY).handle(ByteBuffer.wrap(in), context, result);
        Assert.assertSame(Methods.GET, result.getRequestMethod());
        Assert.assertEquals("/somepath", result.getRequestURI());
        Assert.assertSame(Protocols.HTTP_1_1, result.getProtocol());

        Assert.assertEquals(2, result.getRequestHeaders().getHeaderNames().size());
        Assert.assertEquals("www.somehost.net", result.getRequestHeaders().getFirst(new HttpString("Host")));
        Assert.assertEquals("some value", result.getRequestHeaders().getFirst(new HttpString("OtherHeader")));

        Assert.assertEquals(ParseState.PARSE_COMPLETE, context.state);
    }

}
