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

  @Test public void verifyNonAsciiCn() throws Exception{SSLSession session=session("" + "-----BEGIN CERTIFICATE-----\n" + "MIIESzCCAzOgAwIBAgIJAIz+EYMBU6aTMA0GCSqGSIb3DQEBBQUAMIGiMQswCQYD\n" + "VQQGEwJDQTELMAkGA1UECBMCQkMxEjAQBgNVBAcTCVZhbmNvdXZlcjEWMBQGA1UE\n" + "ChMNd3d3LmN1Y2JjLmNvbTEUMBIGA1UECxQLY29tbW9uc19zc2wxHTAbBgNVBAMU\n" + "FGRlbW9faW50ZXJtZWRpYXRlX2NhMSUwIwYJKoZIhvcNAQkBFhZqdWxpdXNkYXZp\n" + "ZXNAZ21haWwuY29tMB4XDTA2MTIxMTE1NDIxNVoXDTI4MTEwNTE1NDIxNVowgakx\n" + "CzAJBgNVBAYTAlVTMREwDwYDVQQIDAhNYXJ5bGFuZDEUMBIGA1UEBwwLRm9yZXN0\n" + "IEhpbGwxFzAVBgNVBAoMDmh0dHBjb21wb25lbnRzMRowGAYDVQQLDBF0ZXN0IGNl\n" + "cnRpZmljYXRlczEVMBMGA1UEAwwM6Iqx5a2QLmNvLmpwMSUwIwYJKoZIhvcNAQkB\n" + "FhZqdWxpdXNkYXZpZXNAZ21haWwuY29tMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8A\n" + "MIIBCgKCAQEAyGOvloI+jKnRHWKuhYB+cTIEwZhagKJ0f3rIY8WNgujB7PlpgpjU\n" + "g4pNjYGViGjg7zhfbjhCtlNGXyRBti3GcaHiBIIP5nyCNn+Ay8tSWGo5v5Zc8BQc\n" + "wHf0ZHLN6sD9m2uVSp/6UqjS5ZyhzF5FzvvUo3xw8fecdnStXQfHhkBnLpTjHE5t\n" + "7iu1JVjTuE0pcBvah2dWqDNxiIOQtXyKW8Sag1YxaunxQGqRNykSFiEJindxOSAn\n" + "AxK6q/wGqcZ3zvFBTcVVkji1u2QH4rOMP3PPxAIMkB8ONkdHTco1DmbE6BfDHArD\n" + "qUYxqJUlPGlMqrKb3fCFiT3eXehwR7nlzQIDAQABo3sweTAJBgNVHRMEAjAAMCwG\n" + "CWCGSAGG+EIBDQQfFh1PcGVuU1NMIEdlbmVyYXRlZCBDZXJ0aWZpY2F0ZTAdBgNV\n" + "HQ4EFgQUnxR3vz86tso4gkJIFiza0Mteh9gwHwYDVR0jBBgwFoAUe5raj5CZTlLS\n" + "rNuzA1LKh6YNPg0wDQYJKoZIhvcNAQEFBQADggEBALJ27i3okV/KvlDp6KMID3gd\n" + "ITl68PyItzzx+SquF8gahMh016NX73z/oVZoVUNdftla8wPUB1GwIkAnGkhQ9LHK\n" + "spBdbRiCj0gMmLCsX8SrjFvr7cYb2cK6J/fJe92l1tg/7Y4o7V/s4JBe/cy9U9w8\n" + "a0ctuDmEBCgC784JMDtT67klRfr/2LlqWhlOEq7pUFxRLbhpquaAHSOjmIcWnVpw\n" + "9BsO7qe46hidgn39hKh1WjKK2VcL/3YRsC4wUi0PBtFW6ScMCuMhgIRXSPU55Rae\n" + "UIlOdPjjr1SUNWGId1rD7W16Scpwnknn310FNxFMHVI0GTGFkNdkilNCFJcIoRA=\n" + "-----END CERTIFICATE-----\n");assertTrue(verifier.verify("\u82b1\u5b50.co.jp",session));assertFalse(verifier.verify("a.\u82b1\u5b50.co.jp",session));}

  private X509Certificate certificate(String certificate) throws Exception {
    return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(
        new ByteArrayInputStream(certificate.getBytes(Util.UTF_8)));
  }

  private SSLSession session(String certificate) throws Exception {
    return new FakeSSLSession(certificate(certificate));
  }
}
