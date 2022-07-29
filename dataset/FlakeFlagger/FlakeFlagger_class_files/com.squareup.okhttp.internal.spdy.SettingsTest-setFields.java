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
  @Test public void setFields(){Settings settings=new Settings();assertEquals(-3,settings.getUploadBandwidth(-3));assertEquals(-1,settings.getHeaderTableSize());settings.set(UPLOAD_BANDWIDTH,0,42);assertEquals(42,settings.getUploadBandwidth(-3));settings.set(Settings.HEADER_TABLE_SIZE,0,8096);assertEquals(8096,settings.getHeaderTableSize());assertEquals(-3,settings.getDownloadBandwidth(-3));assertEquals(true,settings.getEnablePush(true));settings.set(DOWNLOAD_BANDWIDTH,0,53);assertEquals(53,settings.getDownloadBandwidth(-3));settings.set(Settings.ENABLE_PUSH,0,0);assertEquals(false,settings.getEnablePush(true));assertEquals(-3,settings.getRoundTripTime(-3));settings.set(Settings.ROUND_TRIP_TIME,0,64);assertEquals(64,settings.getRoundTripTime(-3));assertEquals(-3,settings.getMaxConcurrentStreams(-3));settings.set(MAX_CONCURRENT_STREAMS,0,75);assertEquals(75,settings.getMaxConcurrentStreams(-3));assertEquals(-3,settings.getCurrentCwnd(-3));settings.set(Settings.CURRENT_CWND,0,86);assertEquals(86,settings.getCurrentCwnd(-3));assertEquals(-3,settings.getDownloadRetransRate(-3));settings.set(DOWNLOAD_RETRANS_RATE,0,97);assertEquals(97,settings.getDownloadRetransRate(-3));assertEquals(-1,settings.getInitialWindowSize());settings.set(Settings.INITIAL_WINDOW_SIZE,0,108);assertEquals(108,settings.getInitialWindowSize());assertEquals(-3,settings.getClientCertificateVectorSize(-3));settings.set(Settings.CLIENT_CERTIFICATE_VECTOR_SIZE,0,117);assertEquals(117,settings.getClientCertificateVectorSize(-3));}
}
