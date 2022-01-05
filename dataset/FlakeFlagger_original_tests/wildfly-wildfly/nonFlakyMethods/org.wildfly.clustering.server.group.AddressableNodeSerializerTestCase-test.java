@Test public void test() throws ClassNotFoundException, IOException {
  AddressableNode node=new AddressableNode(UUID.randomUUID(),"node1",new InetSocketAddress(InetAddress.getLoopbackAddress(),Short.MAX_VALUE));
  new ExternalizerTester<>(new AddressableNodeExternalizer(),AddressableNodeSerializerTestCase::assertEquals).test(node);
  new KeyFormatTester<>(new AddressableNodeKeyFormat(),AddressableNodeSerializerTestCase::assertEquals).test(node);
}
