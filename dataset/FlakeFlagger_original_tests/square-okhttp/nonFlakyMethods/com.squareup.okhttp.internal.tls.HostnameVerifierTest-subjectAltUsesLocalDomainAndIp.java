@Test public void subjectAltUsesLocalDomainAndIp() throws Exception {
  X509Certificate certificate=certificate("" + "-----BEGIN CERTIFICATE-----\n" + "MIIBWDCCAQKgAwIBAgIJANS1EtICX2AZMA0GCSqGSIb3DQEBBQUAMBQxEjAQBgNV\n"+ "BAMTCWxvY2FsaG9zdDAgFw0xMjAxMDIxOTA4NThaGA8yMTExMTIwOTE5MDg1OFow\n"+ "FDESMBAGA1UEAxMJbG9jYWxob3N0MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAPpt\n"+ "atK8r4/hf4hSIs0os/BSlQLbRBaK9AfBReM4QdAklcQqe6CHsStKfI8pp0zs7Ptg\n"+ "PmMdpbttL0O7mUboBC8CAwEAAaM1MDMwMQYDVR0RBCowKIIVbG9jYWxob3N0Lmxv\n"+ "Y2FsZG9tYWlugglsb2NhbGhvc3SHBH8AAAEwDQYJKoZIhvcNAQEFBQADQQD0ntfL\n"+ "DCzOCv9Ma6Lv5o5jcYWVxvBSTsnt22hsJpWD1K7iY9lbkLwl0ivn73pG2evsAn9G\n"+ "X8YKH52fnHsCrhSD\n"+ "-----END CERTIFICATE-----");
  assertEquals(new X500Principal("CN=localhost"),certificate.getSubjectX500Principal());
  FakeSSLSession session=new FakeSSLSession(certificate);
  assertTrue(verifier.verify("localhost",session));
  assertTrue(verifier.verify("localhost.localdomain",session));
  assertFalse(verifier.verify("local.host",session));
  assertTrue(verifier.verify("127.0.0.1",session));
  assertFalse(verifier.verify("127.0.0.2",session));
}
