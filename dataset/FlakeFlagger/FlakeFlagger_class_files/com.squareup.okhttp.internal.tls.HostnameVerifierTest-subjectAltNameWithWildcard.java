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

  @Test public void subjectAltNameWithWildcard() throws Exception{SSLSession session=session("" + "-----BEGIN CERTIFICATE-----\n" + "MIIBPzCB6qADAgECAgkAnv/7Jv5r7pMwDQYJKoZIhvcNAQEFBQAwEjEQMA4GA1UE\n" + "AxMHZm9vLmNvbTAgFw0xMDEyMjAxODQ2MDFaGA8yMTEwMTEyNjE4NDYwMVowEjEQ\n" + "MA4GA1UEAxMHZm9vLmNvbTBcMA0GCSqGSIb3DQEBAQUAA0sAMEgCQQDAz2YXnyog\n" + "YdYLSFr/OEgSumtwqtZKJTB4wqTW/eKbBCEzxnyUMxWZIqUGu353PzwfOuWp2re3\n" + "nvVV+QDYQlh9AgMBAAGjITAfMB0GA1UdEQQWMBSCB2Jhci5jb22CCSouYmF6LmNv\n" + "bTANBgkqhkiG9w0BAQUFAANBAB8yrSl8zqy07i0SNYx2B/FnvQY734pxioaqFWfO\n" + "Bqo1ZZl/9aPHEWIwBrxYNVB0SGu/kkbt/vxqOjzzrkXukmI=\n" + "-----END CERTIFICATE-----");assertFalse(verifier.verify("foo.com",session));assertTrue(verifier.verify("bar.com",session));assertTrue(verifier.verify("a.baz.com",session));assertTrue(verifier.verify("baz.com",session));assertFalse(verifier.verify("a.foo.com",session));assertFalse(verifier.verify("a.bar.com",session));assertFalse(verifier.verify("quux.com",session));}

  private X509Certificate certificate(String certificate) throws Exception {
    return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(
        new ByteArrayInputStream(certificate.getBytes(Util.UTF_8)));
  }

  private SSLSession session(String certificate) throws Exception {
    return new FakeSSLSession(certificate(certificate));
  }
}
