@Test public void testLookup(){
  List<URL> lookup=zookeeperRegistry.lookup(serviceUrl);
  assertThat(lookup.size(),is(0));
  zookeeperRegistry.register(serviceUrl);
  lookup=zookeeperRegistry.lookup(serviceUrl);
  assertThat(lookup.size(),is(1));
}
