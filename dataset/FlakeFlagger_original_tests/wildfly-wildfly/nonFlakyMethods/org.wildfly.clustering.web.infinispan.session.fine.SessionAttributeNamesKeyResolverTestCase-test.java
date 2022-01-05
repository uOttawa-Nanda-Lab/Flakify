@Test public void test() throws ClassNotFoundException, IOException {
  SessionAttributeNamesKey key=new SessionAttributeNamesKey("ABC123");
  new ExternalizerTester<>(new SessionAttributeNamesKeyExternalizer()).test(key);
  new KeyFormatTester<>(new SessionAttributeNamesKeyFormat()).test(key);
}
