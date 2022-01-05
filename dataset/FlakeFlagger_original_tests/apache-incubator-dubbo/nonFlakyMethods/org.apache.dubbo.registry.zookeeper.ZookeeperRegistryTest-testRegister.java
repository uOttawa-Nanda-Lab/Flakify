@Test public void testRegister(){
  Set<URL> registered;
  for (int i=0; i < 2; i++) {
    zookeeperRegistry.register(serviceUrl);
    registered=zookeeperRegistry.getRegistered();
    assertThat(registered.contains(serviceUrl),is(true));
  }
  registered=zookeeperRegistry.getRegistered();
  assertThat(registered.size(),is(1));
}
