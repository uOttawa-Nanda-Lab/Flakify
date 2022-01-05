@Test public void testRedisConfigurationWithSentinel() throws Exception {
  List<String> sentinels=Arrays.asList("127.0.0.1:26379","127.0.0.1:26380");
  if (isAtLeastOneSentinelAvailable(sentinels)) {
    this.context=new AnnotationConfigApplicationContext();
    EnvironmentTestUtils.addEnvironment(this.context,"spring.redis.sentinel.master:mymaster");
    EnvironmentTestUtils.addEnvironment(this.context,"spring.redis.sentinel.nodes:" + StringUtils.collectionToCommaDelimitedString(sentinels));
    this.context.register(RedisAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
    this.context.refresh();
    assertTrue(this.context.getBean(JedisConnectionFactory.class).isRedisSentinelAware());
  }
}
