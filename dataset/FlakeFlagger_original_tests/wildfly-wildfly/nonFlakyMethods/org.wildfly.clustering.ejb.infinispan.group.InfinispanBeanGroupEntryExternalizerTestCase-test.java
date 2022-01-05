@Test public void test() throws ClassNotFoundException, IOException {
  SessionID id=new UUIDSessionID(UUID.randomUUID());
  Map<SessionID,String> beans=Collections.singletonMap(id,"bean");
  InfinispanBeanGroupEntry<SessionID,String> entry=new InfinispanBeanGroupEntry<>(new SimpleMarshalledValue<>(beans,context));
  new ExternalizerTester<>(new InfinispanBeanGroupEntryExternalizer<>(),InfinispanBeanGroupEntryExternalizerTestCase::assertEquals).test(entry);
}
