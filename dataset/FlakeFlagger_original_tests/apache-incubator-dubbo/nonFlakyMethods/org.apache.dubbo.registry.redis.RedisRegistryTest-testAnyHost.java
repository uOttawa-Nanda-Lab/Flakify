@Test(expected=IllegalStateException.class) public void testAnyHost(){
  URL errorUrl=URL.valueOf("multicast://0.0.0.0/");
  new RedisRegistryFactory().createRegistry(errorUrl);
}
