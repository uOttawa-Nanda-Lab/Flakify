@Test public void testRegister(){
  Set<URL> registered=null;
  for (int i=0; i < 2; i++) {
    redisRegistry.register(serviceUrl);
    registered=redisRegistry.getRegistered();
    assertThat(registered.contains(serviceUrl),is(true));
  }
  registered=redisRegistry.getRegistered();
  assertThat(registered.size(),is(1));
}
