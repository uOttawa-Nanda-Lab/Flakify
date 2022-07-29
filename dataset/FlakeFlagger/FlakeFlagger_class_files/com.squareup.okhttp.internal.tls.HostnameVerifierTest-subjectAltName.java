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

  @Test public void subjectAltName() throws Exception{SSLSession session=session("" + "-----BEGIN CERTIFICATE-----\n" + "MIIBPTCB6KADAgECAgkA7zoHaaqNGHQwDQYJKoZIhvcNAQEFBQAwEjEQMA4GA1UE\n" + "AxMHZm9vLmNvbTAgFw0xMDEyMjAxODM5MzZaGA8yMTEwMTEyNjE4MzkzNlowEjEQ\n" + "MA4GA1UEAxMHZm9vLmNvbTBcMA0GCSqGSIb3DQEBAQUAA0sAMEgCQQC+gmoSxF+8\n" + "hbV+rgRQqHIJd50216OWQJbU3BvdlPbca779NYO4+UZWTFdBM8BdQqs3H4B5Agvp\n" + "y7HeSff1F7XRAgMBAAGjHzAdMBsGA1UdEQQUMBKCB2Jhci5jb22CB2Jhei5jb20w\n" + "DQYJKoZIhvcNAQEFBQADQQBXpZZPOY2Dy1lGG81JTr8L4or9jpKacD7n51eS8iqI\n" + "oTznPNuXHU5bFN0AAGX2ij47f/EahqTpo5RdS95P4sVm\n" + "-----END CERTIFICATE-----");assertFalse(verifier.verify("foo.com",session));assertTrue(verifier.verify("bar.com",session));assertTrue(verifier.verify("baz.com",session));assertFalse(verifier.verify("a.foo.com",session));assertFalse(verifier.verify("quux.com",session));}

  private X509Certificate certificate(String certificate) throws Exception {
    return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(
        new ByteArrayInputStream(certificate.getBytes(Util.UTF_8)));
  }

  private SSLSession session(String certificate) throws Exception {
    return new FakeSSLSession(certificate(certificate));
  }
}
