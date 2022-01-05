@Test public void redisIsUp() throws Exception {
  Properties info=new Properties();
  info.put("redis_version","2.8.9");
  RedisConnection redisConnection=mock(RedisConnection.class);
  RedisConnectionFactory redisConnectionFactory=mock(RedisConnectionFactory.class);
  given(redisConnectionFactory.getConnection()).willReturn(redisConnection);
  given(redisConnection.info()).willReturn(info);
  RedisHealthIndicator healthIndicator=new RedisHealthIndicator(redisConnectionFactory);
  Health health=healthIndicator.health();
  assertEquals(Status.UP,health.getStatus());
  assertEquals("2.8.9",health.getDetails().get("version"));
  verify(redisConnectionFactory).getConnection();
  verify(redisConnection).info();
}
