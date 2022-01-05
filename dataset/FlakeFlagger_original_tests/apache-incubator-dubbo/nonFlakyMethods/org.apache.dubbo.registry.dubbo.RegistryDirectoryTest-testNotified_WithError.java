@Test public void testNotified_WithError(){
  RegistryDirectory registryDirectory=getRegistryDirectory();
  List<URL> serviceUrls=new ArrayList<URL>();
  URL badurl=URL.valueOf("notsupported://127.0.0.1/" + service);
  serviceUrls.add(badurl);
  serviceUrls.add(SERVICEURL);
  registryDirectory.notify(serviceUrls);
  Assert.assertEquals(true,registryDirectory.isAvailable());
  List invokers=registryDirectory.list(invocation);
  Assert.assertEquals(1,invokers.size());
}
