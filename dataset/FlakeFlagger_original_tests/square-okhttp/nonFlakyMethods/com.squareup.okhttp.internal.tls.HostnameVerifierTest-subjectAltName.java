@Test public void subjectAltName() throws Exception {
  SSLSession session=session("" + "-----BEGIN CERTIFICATE-----\n" + "MIIBPTCB6KADAgECAgkA7zoHaaqNGHQwDQYJKoZIhvcNAQEFBQAwEjEQMA4GA1UE\n"+ "AxMHZm9vLmNvbTAgFw0xMDEyMjAxODM5MzZaGA8yMTEwMTEyNjE4MzkzNlowEjEQ\n"+ "MA4GA1UEAxMHZm9vLmNvbTBcMA0GCSqGSIb3DQEBAQUAA0sAMEgCQQC+gmoSxF+8\n"+ "hbV+rgRQqHIJd50216OWQJbU3BvdlPbca779NYO4+UZWTFdBM8BdQqs3H4B5Agvp\n"+ "y7HeSff1F7XRAgMBAAGjHzAdMBsGA1UdEQQUMBKCB2Jhci5jb22CB2Jhei5jb20w\n"+ "DQYJKoZIhvcNAQEFBQADQQBXpZZPOY2Dy1lGG81JTr8L4or9jpKacD7n51eS8iqI\n"+ "oTznPNuXHU5bFN0AAGX2ij47f/EahqTpo5RdS95P4sVm\n"+ "-----END CERTIFICATE-----");
  assertFalse(verifier.verify("foo.com",session));
  assertTrue(verifier.verify("bar.com",session));
  assertTrue(verifier.verify("baz.com",session));
  assertFalse(verifier.verify("a.foo.com",session));
  assertFalse(verifier.verify("quux.com",session));
}
