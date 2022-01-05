@Test public void subjectAltNameWithWildcard() throws Exception {
  SSLSession session=session("" + "-----BEGIN CERTIFICATE-----\n" + "MIIBPzCB6qADAgECAgkAnv/7Jv5r7pMwDQYJKoZIhvcNAQEFBQAwEjEQMA4GA1UE\n"+ "AxMHZm9vLmNvbTAgFw0xMDEyMjAxODQ2MDFaGA8yMTEwMTEyNjE4NDYwMVowEjEQ\n"+ "MA4GA1UEAxMHZm9vLmNvbTBcMA0GCSqGSIb3DQEBAQUAA0sAMEgCQQDAz2YXnyog\n"+ "YdYLSFr/OEgSumtwqtZKJTB4wqTW/eKbBCEzxnyUMxWZIqUGu353PzwfOuWp2re3\n"+ "nvVV+QDYQlh9AgMBAAGjITAfMB0GA1UdEQQWMBSCB2Jhci5jb22CCSouYmF6LmNv\n"+ "bTANBgkqhkiG9w0BAQUFAANBAB8yrSl8zqy07i0SNYx2B/FnvQY734pxioaqFWfO\n"+ "Bqo1ZZl/9aPHEWIwBrxYNVB0SGu/kkbt/vxqOjzzrkXukmI=\n"+ "-----END CERTIFICATE-----");
  assertFalse(verifier.verify("foo.com",session));
  assertTrue(verifier.verify("bar.com",session));
  assertTrue(verifier.verify("a.baz.com",session));
  assertTrue(verifier.verify("baz.com",session));
  assertFalse(verifier.verify("a.foo.com",session));
  assertFalse(verifier.verify("a.bar.com",session));
  assertFalse(verifier.verify("quux.com",session));
}
