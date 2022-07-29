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
package com.squareup.okhttp.internal.spdy;

import org.junit.Test;

import static com.squareup.okhttp.internal.spdy.Settings.DOWNLOAD_BANDWIDTH;
import static com.squareup.okhttp.internal.spdy.Settings.DOWNLOAD_RETRANS_RATE;
import static com.squareup.okhttp.internal.spdy.Settings.MAX_CONCURRENT_STREAMS;
import static com.squareup.okhttp.internal.spdy.Settings.PERSISTED;
import static com.squareup.okhttp.internal.spdy.Settings.PERSIST_VALUE;
import static com.squareup.okhttp.internal.spdy.Settings.UPLOAD_BANDWIDTH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class SettingsTest {
  @Test public void merge(){Settings a=new Settings();a.set(UPLOAD_BANDWIDTH,PERSIST_VALUE,100);a.set(DOWNLOAD_BANDWIDTH,PERSIST_VALUE,200);a.set(DOWNLOAD_RETRANS_RATE,0,300);Settings b=new Settings();b.set(DOWNLOAD_BANDWIDTH,0,400);b.set(DOWNLOAD_RETRANS_RATE,PERSIST_VALUE,500);b.set(MAX_CONCURRENT_STREAMS,PERSIST_VALUE,600);a.merge(b);assertEquals(100,a.getUploadBandwidth(-1));assertEquals(PERSIST_VALUE,a.flags(UPLOAD_BANDWIDTH));assertEquals(400,a.getDownloadBandwidth(-1));assertEquals(0,a.flags(DOWNLOAD_BANDWIDTH));assertEquals(500,a.getDownloadRetransRate(-1));assertEquals(PERSIST_VALUE,a.flags(DOWNLOAD_RETRANS_RATE));assertEquals(600,a.getMaxConcurrentStreams(-1));assertEquals(PERSIST_VALUE,a.flags(MAX_CONCURRENT_STREAMS));}
}
