@Test public void test() throws ClassNotFoundException, IOException {
  SessionAttributeKey key=new SessionAttributeKey("ABC123",10);
  new ExternalizerTester<>(new SessionAttributeKeyExternalizer()).test(key);
  new KeyFormatTester<>(new SessionAttributeKeyFormat()).test(key);
}
