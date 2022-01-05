@Test public void test() throws ClassNotFoundException, IOException {
  InfinispanBeanKey<SessionID> key=new InfinispanBeanKey<>(new UUIDSessionID(UUID.randomUUID()));
  new ExternalizerTester<>(new InfinispanBeanKeyExternalizer()).test(key);
  new KeyFormatTester<>(new InfinispanBeanKeyFormat()).test(key);
}
