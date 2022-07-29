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
  @Test public void readNameValueBlockDropsForbiddenHeadersHttp2() throws IOException{List<Header> headerBlock=headerEntries(":status","200 OK",":version","HTTP/1.1","connection","close");Request request=new Request.Builder().url("http://square.com/").build();Response response=SpdyTransport.readNameValueBlock(headerBlock,Protocol.HTTP_2).request(request).build();Headers headers=response.headers();assertEquals(1,headers.size());assertEquals(OkHeaders.SELECTED_PROTOCOL,headers.name(0));assertEquals(Protocol.HTTP_2.name.utf8(),headers.value(0));}
}
