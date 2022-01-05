@Test public void test(){
  KeyMapperTester tester=new KeyMapperTester(new KeyMapper());
  SessionID id=new UUIDSessionID(UUID.randomUUID());
  tester.test(new InfinispanBeanKey<>(id));
  tester.test(new InfinispanBeanGroupKey<>(id));
}
