@Test public void testAvailable(){
  zookeeperRegistry.register(serviceUrl);
  assertThat(zookeeperRegistry.isAvailable(),is(true));
  zookeeperRegistry.destroy();
  assertThat(zookeeperRegistry.isAvailable(),is(false));
}
