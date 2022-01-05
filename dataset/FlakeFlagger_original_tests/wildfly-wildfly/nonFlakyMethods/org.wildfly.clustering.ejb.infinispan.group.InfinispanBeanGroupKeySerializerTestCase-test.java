@Test public void test() throws ClassNotFoundException, IOException {
  InfinispanBeanGroupKey<SessionID> key=new InfinispanBeanGroupKey<>(new UUIDSessionID(UUID.randomUUID()));
  new ExternalizerTester<>(new InfinispanBeanGroupKeyExternalizer()).test(key);
  new KeyFormatTester<>(new InfinispanBeanGroupKeyFormat()).test(key);
}
