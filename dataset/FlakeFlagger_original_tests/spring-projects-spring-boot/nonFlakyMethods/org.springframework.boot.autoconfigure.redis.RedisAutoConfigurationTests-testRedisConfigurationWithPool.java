@Test public void testRedisConfigurationWithPool() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.redis.host:foo");
  EnvironmentTestUtils.addEnvironment(this.context,"spring.redis.pool.max-idle:1");
  this.context.register(RedisAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertEquals("foo",this.context.getBean(JedisConnectionFactory.class).getHostName());
  assertEquals(1,this.context.getBean(JedisConnectionFactory.class).getPoolConfig().getMaxIdle());
}
