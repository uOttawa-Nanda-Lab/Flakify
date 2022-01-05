@Test public void testRegistryFactoryIpCache() throws Exception {
  Registry registry1=registryFactory.getRegistry(URL.valueOf("dubbo://" + NetUtils.getLocalAddress().getHostName() + ":2233"));
  Registry registry2=registryFactory.getRegistry(URL.valueOf("dubbo://" + NetUtils.getLocalAddress().getHostAddress() + ":2233"));
  Assert.assertEquals(registry1,registry2);
}
