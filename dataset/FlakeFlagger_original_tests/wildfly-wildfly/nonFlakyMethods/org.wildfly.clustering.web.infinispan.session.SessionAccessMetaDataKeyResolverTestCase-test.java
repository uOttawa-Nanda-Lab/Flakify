@Test public void test() throws ClassNotFoundException, IOException {
  SessionAccessMetaDataKey key=new SessionAccessMetaDataKey("ABC123");
  new ExternalizerTester<>(new SessionAccessMetaDataKeyExternalizer()).test(key);
  new KeyFormatTester<>(new SessionAccessMetaDataKeyFormat()).test(key);
}
