@Test public void test(){
  KeyMapperTester tester=new KeyMapperTester(new KeyMapper());
  tester.test(new LocalNode("node"));
  tester.test(new AddressableNode(UUID.randomUUID(),"node",new InetSocketAddress(InetAddress.getLoopbackAddress(),Short.MAX_VALUE)));
  tester.test(ServiceName.JBOSS.append("service"));
}
