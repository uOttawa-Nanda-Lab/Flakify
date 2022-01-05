@Test public void testDefaultRedisConfiguration() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(RedisAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean("redisTemplate",RedisOperations.class));
  assertNotNull(this.context.getBean(StringRedisTemplate.class));
}
