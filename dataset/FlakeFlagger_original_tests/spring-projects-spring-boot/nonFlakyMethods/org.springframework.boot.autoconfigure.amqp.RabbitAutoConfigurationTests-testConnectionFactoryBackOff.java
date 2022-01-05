@Test public void testConnectionFactoryBackOff(){
  load(TestConfiguration2.class);
  RabbitTemplate rabbitTemplate=this.context.getBean(RabbitTemplate.class);
  CachingConnectionFactory connectionFactory=this.context.getBean(CachingConnectionFactory.class);
  assertEquals(rabbitTemplate.getConnectionFactory(),connectionFactory);
  assertEquals("otherserver",connectionFactory.getHost());
  assertEquals(8001,connectionFactory.getPort());
}
