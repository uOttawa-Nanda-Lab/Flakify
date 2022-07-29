/*
 * Copyright (C) 2012 Square, Inc.
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
package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.spdy.Header;
import java.io.IOException;
import java.util.List;
import org.junit.Test;

import static com.squareup.okhttp.internal.Util.headerEntries;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public final class HeadersTest {
  @Test public void toNameValueBlockDropsForbiddenHeadersSpdy3(){Request request=new Request.Builder().url("http://square.com/").header("Connection","close").header("Transfer-Encoding","chunked").build();List<Header> expected=headerEntries(":method","GET",":path","/",":version","HTTP/1.1",":host","square.com",":scheme","http");assertEquals(expected,SpdyTransport.writeNameValueBlock(request,Protocol.SPDY_3,"HTTP/1.1"));}
}
