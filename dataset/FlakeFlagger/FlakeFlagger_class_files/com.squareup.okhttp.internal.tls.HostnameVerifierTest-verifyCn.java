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

  @Test public void verifyCn() throws Exception{SSLSession session=session("" + "-----BEGIN CERTIFICATE-----\n" + "MIIERjCCAy6gAwIBAgIJAIz+EYMBU6aQMA0GCSqGSIb3DQEBBQUAMIGiMQswCQYD\n" + "VQQGEwJDQTELMAkGA1UECBMCQkMxEjAQBgNVBAcTCVZhbmNvdXZlcjEWMBQGA1UE\n" + "ChMNd3d3LmN1Y2JjLmNvbTEUMBIGA1UECxQLY29tbW9uc19zc2wxHTAbBgNVBAMU\n" + "FGRlbW9faW50ZXJtZWRpYXRlX2NhMSUwIwYJKoZIhvcNAQkBFhZqdWxpdXNkYXZp\n" + "ZXNAZ21haWwuY29tMB4XDTA2MTIxMTE1MzE0MVoXDTI4MTEwNTE1MzE0MVowgaQx\n" + "CzAJBgNVBAYTAlVTMREwDwYDVQQIEwhNYXJ5bGFuZDEUMBIGA1UEBxMLRm9yZXN0\n" + "IEhpbGwxFzAVBgNVBAoTDmh0dHBjb21wb25lbnRzMRowGAYDVQQLExF0ZXN0IGNl\n" + "cnRpZmljYXRlczEQMA4GA1UEAxMHZm9vLmNvbTElMCMGCSqGSIb3DQEJARYWanVs\n" + "aXVzZGF2aWVzQGdtYWlsLmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoC\n" + "ggEBAMhjr5aCPoyp0R1iroWAfnEyBMGYWoCidH96yGPFjYLowez5aYKY1IOKTY2B\n" + "lYho4O84X244QrZTRl8kQbYtxnGh4gSCD+Z8gjZ/gMvLUlhqOb+WXPAUHMB39GRy\n" + "zerA/ZtrlUqf+lKo0uWcocxeRc771KN8cPH3nHZ0rV0Hx4ZAZy6U4xxObe4rtSVY\n" + "07hNKXAb2odnVqgzcYiDkLV8ilvEmoNWMWrp8UBqkTcpEhYhCYp3cTkgJwMSuqv8\n" + "BqnGd87xQU3FVZI4tbtkB+KzjD9zz8QCDJAfDjZHR03KNQ5mxOgXwxwKw6lGMaiV\n" + "JTxpTKqym93whYk93l3ocEe55c0CAwEAAaN7MHkwCQYDVR0TBAIwADAsBglghkgB\n" + "hvhCAQ0EHxYdT3BlblNTTCBHZW5lcmF0ZWQgQ2VydGlmaWNhdGUwHQYDVR0OBBYE\n" + "FJ8Ud78/OrbKOIJCSBYs2tDLXofYMB8GA1UdIwQYMBaAFHua2o+QmU5S0qzbswNS\n" + "yoemDT4NMA0GCSqGSIb3DQEBBQUAA4IBAQC3jRmEya6sQCkmieULcvx8zz1euCk9\n" + "fSez7BEtki8+dmfMXe3K7sH0lI8f4jJR0rbSCjpmCQLYmzC3NxBKeJOW0RcjNBpO\n" + "c2JlGO9auXv2GDP4IYiXElLJ6VSqc8WvDikv0JmCCWm0Zga+bZbR/EWN5DeEtFdF\n" + "815CLpJZNcYwiYwGy/CVQ7w2TnXlG+mraZOz+owr+cL6J/ZesbdEWfjoS1+cUEhE\n" + "HwlNrAu8jlZ2UqSgskSWlhYdMTAP9CPHiUv9N7FcT58Itv/I4fKREINQYjDpvQcx\n" + "SaTYb9dr5sB4WLNglk7zxDtM80H518VvihTcP7FHL+Gn6g4j5fkI98+S\n" + "-----END CERTIFICATE-----\n");assertTrue(verifier.verify("foo.com",session));assertFalse(verifier.verify("a.foo.com",session));assertFalse(verifier.verify("bar.com",session));}

  private X509Certificate certificate(String certificate) throws Exception {
    return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(
        new ByteArrayInputStream(certificate.getBytes(Util.UTF_8)));
  }

  private SSLSession session(String certificate) throws Exception {
    return new FakeSSLSession(certificate(certificate));
  }
}
