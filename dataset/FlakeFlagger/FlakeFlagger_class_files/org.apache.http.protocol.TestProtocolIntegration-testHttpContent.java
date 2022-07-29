/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.Header;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.testserver.HttpClient;
import org.apache.http.testserver.HttpServer;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestProtocolIntegration {

    private HttpServer server;
    private HttpClient client;

    @Before
    public void initServer() throws Exception {
        this.server = new HttpServer();
    }

    @Before
    public void initClient() throws Exception {
        this.client = new HttpClient();
    }

    static class RepeatingEntity extends AbstractHttpEntity {

        private final byte[] raw;
        private int n;

        public RepeatingEntity(final String content, String charset, int n) {
            super();
            if (content == null) {
                throw new IllegalArgumentException("Content may not be null");
            }
            if (n <= 0) {
                throw new IllegalArgumentException("N may not be negative or zero");
            }
            if (charset == null) {
                charset = "US-ASCII";
            }
            byte[] b;
            try {
                b = content.getBytes(charset);
            } catch (UnsupportedEncodingException ex) {
                b = content.getBytes();
            }
            this.raw = b;
            this.n = n;
        }

        public InputStream getContent() throws IOException, IllegalStateException {
            throw new IllegalStateException("This method is not implemented");
        }

        public long getContentLength() {
            return (this.raw.length + 2) * this.n;
        }

        public boolean isRepeatable() {
            return true;
        }

        public boolean isStreaming() {
            return false;
        }

        public void writeTo(final OutputStream outstream) throws IOException {
            for (int i = 0; i < this.n; i++) {
                outstream.write(this.raw);
                outstream.write('\r');
                outstream.write('\n');
            }
            outstream.flush();
        }

    }

    @Test public void testHttpContent() throws Exception{String[] patterns={"0123456789ABCDEF","yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that-" + "yadayada-blahblah-this-and-that-yadayada-blahblah-this-and-that"};this.server.registerHandler("*",new HttpRequestHandler(){public void handle(final HttpRequest request,final HttpResponse response,final HttpContext context) throws HttpException,IOException{if (request instanceof HttpEntityEnclosingRequest){int n=1;String s=request.getRequestLine().getUri();if (s.startsWith("/?n=")){s=s.substring(4);try {n=Integer.parseInt(s);if (n <= 0){throw new HttpException("Invalid request: " + "number of repetitions cannot be negative or zero");}} catch (NumberFormatException ex){throw new HttpException("Invalid request: " + "number of repetitions is invalid");}}HttpEntity incoming=((HttpEntityEnclosingRequest)request).getEntity();String line=EntityUtils.toString(incoming);ContentType contentType=ContentType.getOrDefault(incoming);String charset=contentType.getCharset();if (charset == null){charset=HTTP.DEFAULT_CONTENT_CHARSET;}RepeatingEntity outgoing=new RepeatingEntity(line,charset,n);outgoing.setChunked(n % 2 == 0);response.setEntity(outgoing);} else {throw new HttpException("Invalid request: POST request expected");}}});this.server.start();DefaultHttpClientConnection conn=new DefaultHttpClientConnection();HttpHost host=new HttpHost("localhost",this.server.getPort());try {for (int i=0;i < patterns.length;i++){String pattern=patterns[i];for (int n=1000;n < 1020;n++){if (!conn.isOpen()){Socket socket=new Socket(host.getHostName(),host.getPort());conn.bind(socket,this.client.getParams());}BasicHttpEntityEnclosingRequest post=new BasicHttpEntityEnclosingRequest("POST","/?n=" + n);StringEntity outgoing=new StringEntity(pattern);outgoing.setChunked(n % 2 == 0);post.setEntity(outgoing);HttpResponse response=this.client.execute(post,host,conn);HttpEntity incoming=response.getEntity();Assert.assertNotNull(incoming);InputStream instream=incoming.getContent();ContentType contentType=ContentType.getOrDefault(incoming);String charset=contentType.getCharset();if (charset == null){charset=HTTP.DEFAULT_CONTENT_CHARSET;}Assert.assertNotNull(instream);BufferedReader reader=new BufferedReader(new InputStreamReader(instream,charset));String line;int count=0;while ((line=reader.readLine()) != null){Assert.assertEquals(pattern,line);count++;}Assert.assertEquals(n,count);if (!this.client.keepAlive(response)){conn.close();}}}}  finally {conn.close();this.server.shutdown();}}

}
