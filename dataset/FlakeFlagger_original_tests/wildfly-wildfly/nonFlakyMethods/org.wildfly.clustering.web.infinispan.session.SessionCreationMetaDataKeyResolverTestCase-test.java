@Test public void test() throws ClassNotFoundException, IOException {
  SessionCreationMetaDataKey key=new SessionCreationMetaDataKey("ABC123");
  new ExternalizerTester<>(new SessionCreationMetaDataKeyExternalizer()).test(key);
  new KeyFormatTester<>(new SessionCreationMetaDataKeyFormat()).test(key);
}
