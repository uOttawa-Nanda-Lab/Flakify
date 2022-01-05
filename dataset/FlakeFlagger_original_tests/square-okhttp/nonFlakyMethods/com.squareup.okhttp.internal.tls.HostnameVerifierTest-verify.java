@Test public void verify() throws Exception {
  FakeSSLSession session=new FakeSSLSession();
  assertFalse(verifier.verify("localhost",session));
}
