@Test public void testNotified_WithDuplicateUrls(){
  List<URL> serviceUrls=new ArrayList<URL>();
  serviceUrls.add(SERVICEURL);
  serviceUrls.add(SERVICEURL);
  RegistryDirectory registryDirectory=getRegistryDirectory();
  registryDirectory.notify(serviceUrls);
  List invokers=registryDirectory.list(invocation);
  Assert.assertEquals(1,invokers.size());
}
