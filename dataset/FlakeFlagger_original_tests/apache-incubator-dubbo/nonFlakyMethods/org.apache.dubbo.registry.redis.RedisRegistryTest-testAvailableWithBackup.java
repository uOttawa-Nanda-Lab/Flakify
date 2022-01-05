@Test public void testAvailableWithBackup(){
  URL url=URL.valueOf("redis://redisOne:8880").addParameter(BACKUP_KEY,"redisTwo:8881");
  Registry registry=new RedisRegistryFactory().createRegistry(url);
  assertThat(registry.isAvailable(),is(false));
  url=URL.valueOf(this.registryUrl.toFullString()).addParameter(BACKUP_KEY,"redisTwo:8881");
  registry=new RedisRegistryFactory().createRegistry(url);
  assertThat(registry.isAvailable(),is(true));
}
