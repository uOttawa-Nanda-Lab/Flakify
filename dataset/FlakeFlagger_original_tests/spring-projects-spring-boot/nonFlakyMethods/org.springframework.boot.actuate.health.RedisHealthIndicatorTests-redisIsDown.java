@Test public void redisIsDown() throws Exception {
  RedisConnection redisConnection=mock(RedisConnection.class);
  RedisConnectionFactory redisConnectionFactory=mock(RedisConnectionFactory.class);
  given(redisConnectionFactory.getConnection()).willReturn(redisConnection);
  given(redisConnection.info()).willThrow(new RedisConnectionFailureException("Connection failed"));
  RedisHealthIndicator healthIndicator=new RedisHealthIndicator(redisConnectionFactory);
  Health health=healthIndicator.health();
  assertEquals(Status.DOWN,health.getStatus());
  assertTrue(((String)health.getDetails().get("error")).contains("Connection failed"));
  verify(redisConnectionFactory).getConnection();
  verify(redisConnection).info();
}
