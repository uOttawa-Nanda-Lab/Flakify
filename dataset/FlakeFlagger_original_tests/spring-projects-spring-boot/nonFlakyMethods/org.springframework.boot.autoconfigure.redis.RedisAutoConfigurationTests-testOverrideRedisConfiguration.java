@Test public void testOverrideRedisConfiguration() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.redis.host:foo");
  EnvironmentTestUtils.addEnvironment(this.context,"spring.redis.database:1");
  this.context.register(RedisAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertEquals("foo",this.context.getBean(JedisConnectionFactory.class).getHostName());
  assertEquals(1,this.context.getBean(JedisConnectionFactory.class).getDatabase());
}
