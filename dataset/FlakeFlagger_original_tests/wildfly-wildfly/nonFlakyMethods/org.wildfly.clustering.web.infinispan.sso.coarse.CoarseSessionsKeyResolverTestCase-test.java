@Test public void test() throws ClassNotFoundException, IOException {
  CoarseSessionsKey key=new CoarseSessionsKey("ABC123");
  new ExternalizerTester<>(new CoarseSessionsKeyExternalizer()).test(key);
  new KeyFormatTester<>(new CoarseSessionsKeyFormat()).test(key);
}
