@Test public void testRegistryFactoryCache() throws Exception {
  URL url=URL.valueOf("dubbo://" + NetUtils.getLocalAddress().getHostAddress() + ":2233");
  Registry registry1=registryFactory.getRegistry(url);
  Registry registry2=registryFactory.getRegistry(url);
  Assert.assertEquals(registry1,registry2);
}
