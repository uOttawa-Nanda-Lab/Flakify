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

package io.undertow.client.http;

import io.undertow.util.HttpString;
import io.undertow.util.Protocols;
import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * Tests that the parser can resume when it is given partial input
 *
 * @author Stuart Douglas
 */
public class ResponseParserResumeTestCase {

    public static final String DATA = "HTTP/1.1 200 OK\r\nHost:   www.somehost.net\r\nOtherHeader: some\r\n    value\r\nHostee:another\r\nAccept-garbage:   a\r\n\r\ntttt";

    @Test public void testOneCharacterAtATime(){byte[] in=DATA.getBytes();final ResponseParseState context=new ResponseParseState();HttpResponseBuilder result=new HttpResponseBuilder();ByteBuffer buffer=ByteBuffer.wrap(in);buffer.limit(1);while (context.state != ResponseParseState.PARSE_COMPLETE){HttpResponseParser.INSTANCE.handle(buffer,context,result);buffer.limit(buffer.limit() + 1);}runAssertions(result,context);}

    private void runAssertions(final HttpResponseBuilder result, final ResponseParseState context) {
        Assert.assertEquals(200, result.getStatusCode());
        Assert.assertEquals("OK", result.getReasonPhrase());
        Assert.assertSame(Protocols.HTTP_1_1, result.getProtocol());

        Assert.assertEquals("www.somehost.net", result.getResponseHeaders().getFirst(new HttpString("Host")));
        Assert.assertEquals("some value", result.getResponseHeaders().getFirst(new HttpString("OtherHeader")));
        Assert.assertEquals("another", result.getResponseHeaders().getFirst(new HttpString("Hostee")));
        Assert.assertEquals("a", result.getResponseHeaders().getFirst(new HttpString("Accept-garbage")));
        Assert.assertEquals(4, result.getResponseHeaders().getHeaderNames().size());

        Assert.assertEquals(ResponseParseState.PARSE_COMPLETE, context.state);
    }

}
