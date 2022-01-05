@Test public void testAvailable(){
  redisRegistry.register(serviceUrl);
  assertThat(redisRegistry.isAvailable(),is(true));
  redisRegistry.destroy();
  assertThat(redisRegistry.isAvailable(),is(false));
}
