@Test public void test() throws ClassNotFoundException, IOException {
  AuthenticationKey key=new AuthenticationKey("ABC123");
  new ExternalizerTester<>(new AuthenticationKeyExternalizer()).test(key);
  new KeyFormatTester<>(new AuthenticationKeyFormat()).test(key);
}
