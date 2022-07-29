/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.squareup.okhttp.internal.tls;

import com.squareup.okhttp.internal.Util;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.security.auth.x500.X500Principal;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for our hostname verifier. Most of these tests are from AOSP, which
 * itself includes tests from the Apache HTTP Client test suite.
 */
public final class HostnameVerifierTest {
  private HostnameVerifier verifier = OkHostnameVerifier.INSTANCE;

  /**
 * Earlier implementations of Android's hostname verifier required that wildcard names wouldn't match "*.com" or similar. This was a nonstandard check that we've since dropped. It is the CA's responsibility to not hand out certificates that match so broadly.
 */@Test public void wildcardsDoesNotNeedTwoDots() throws Exception{SSLSession session=session("" + "-----BEGIN CERTIFICATE-----\n" + "MIIBjDCCATagAwIBAgIJAOVulXCSu6HuMA0GCSqGSIb3DQEBBQUAMBAxDjAMBgNV\n" + "BAMUBSouY29tMCAXDTEwMTIyMDE2NDkzOFoYDzIxMTAxMTI2MTY0OTM4WjAQMQ4w\n" + "DAYDVQQDFAUqLmNvbTBcMA0GCSqGSIb3DQEBAQUAA0sAMEgCQQDJd8xqni+h7Iaz\n" + "ypItivs9kPuiJUqVz+SuJ1C05SFc3PmlRCvwSIfhyD67fHcbMdl+A/LrIjhhKZJe\n" + "1joO0+pFAgMBAAGjcTBvMB0GA1UdDgQWBBS4Iuzf5w8JdCp+EtBfdFNudf6+YzBA\n" + "BgNVHSMEOTA3gBS4Iuzf5w8JdCp+EtBfdFNudf6+Y6EUpBIwEDEOMAwGA1UEAxQF\n" + "Ki5jb22CCQDlbpVwkruh7jAMBgNVHRMEBTADAQH/MA0GCSqGSIb3DQEBBQUAA0EA\n" + "U6LFxmZr31lFyis2/T68PpjAppc0DpNQuA2m/Y7oTHBDi55Fw6HVHCw3lucuWZ5d\n" + "qUYo4ES548JdpQtcLrW2sA==\n" + "-----END CERTIFICATE-----");assertTrue(verifier.verify("google.com",session));}

  private X509Certificate certificate(String certificate) throws Exception {
    return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(
        new ByteArrayInputStream(certificate.getBytes(Util.UTF_8)));
  }

  private SSLSession session(String certificate) throws Exception {
    return new FakeSSLSession(certificate(certificate));
  }
}
