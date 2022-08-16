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


    @Test public void testQueryParams(){byte[] in="GET\thttp://www.somehost.net/somepath?a=b&b=c&d&e&f=\tHTTP/1.1\nHost: \t www.somehost.net\nOtherHeader:\tsome\n \t  value\n\r\n".getBytes();final ParseState context=new ParseState();HttpServerExchange result=new HttpServerExchange(null);HttpRequestParser.instance(OptionMap.EMPTY).handle(ByteBuffer.wrap(in),context,result);Assert.assertEquals("/somepath",result.getRelativePath());Assert.assertEquals("http://www.somehost.net/somepath",result.getRequestURI());Assert.assertEquals("a=b&b=c&d&e&f=",result.getQueryString());Assert.assertEquals("b",result.getQueryParameters().get("a").getFirst());Assert.assertEquals("c",result.getQueryParameters().get("b").getFirst());Assert.assertEquals("",result.getQueryParameters().get("d").getFirst());Assert.assertEquals("",result.getQueryParameters().get("e").getFirst());Assert.assertEquals("",result.getQueryParameters().get("f").getFirst());}

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
