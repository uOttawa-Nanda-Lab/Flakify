@Test public void test() throws ClassNotFoundException, IOException {
  InfinispanBeanEntry<SessionID> entry=new InfinispanBeanEntry<>("StatefulBean",new UUIDSessionID(UUID.randomUUID()));
  new ExternalizerTester<>(new InfinispanBeanEntryExternalizer(),InfinispanBeanEntryExternalizerTestCase::assertEquals).test(entry);
}
