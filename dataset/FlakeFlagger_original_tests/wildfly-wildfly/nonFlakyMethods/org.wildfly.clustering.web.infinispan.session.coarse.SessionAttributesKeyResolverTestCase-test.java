@Test public void test() throws ClassNotFoundException, IOException {
  SessionAttributesKey key=new SessionAttributesKey("ABC123");
  new ExternalizerTester<>(new SessionAttributesKeyExternalizer()).test(key);
  new KeyFormatTester<>(new SessionAttributesKeyFormat()).test(key);
}
