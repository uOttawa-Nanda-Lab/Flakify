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
package org.apache.http.impl.pool;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.net.ServerSocket;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TestBasicConnPool {

    private BasicConnPool pool;
    private HttpHost host;
    @Mock private HttpParams params;
    private HttpClientConnection conn = null;

    private ServerSocket server;
    private int serverPort;

    private SSLServerSocket sslServer;
    private int sslServerPort;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // setup an "http" server
        server = new ServerSocket(0);
        serverPort = server.getLocalPort();

        // setup an "https" server
        sslServer = (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket(0);
        sslServerPort = sslServer.getLocalPort();

        pool = new BasicConnPool(params);

        when(params.getIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 0)).thenReturn(100);
        when(params.getIntParameter(CoreConnectionPNames.SO_TIMEOUT, 0)).thenReturn(100);

    }

    @Test public void testHttpCreateConnection() throws Exception{host=new HttpHost("localhost",serverPort,"http");conn=pool.createConnection(host);assertEquals(true,conn.isOpen());assertEquals(100,conn.getSocketTimeout());}

}
