@Test public void test() throws ClassNotFoundException, IOException {
  ClientMapping mapping=new ClientMapping(InetAddress.getLoopbackAddress(),16,"localhost",Short.MAX_VALUE);
  new ExternalizerTester<>(new ClientMappingExternalizer(),ClientMappingExternalizerTestCase::assertEquals).test(mapping);
}
