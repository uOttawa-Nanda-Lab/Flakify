@Test public void testRegistryFactoryGroupCache() throws Exception {
  Registry registry1=registryFactory.getRegistry(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":2233?group=aaa"));
  Registry registry2=registryFactory.getRegistry(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":2233?group=bbb"));
  Assert.assertNotSame(registry1,registry2);
}
