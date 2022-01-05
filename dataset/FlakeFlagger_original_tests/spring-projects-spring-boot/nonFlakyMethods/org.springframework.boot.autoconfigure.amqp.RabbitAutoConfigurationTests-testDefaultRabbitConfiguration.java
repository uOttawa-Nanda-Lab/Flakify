@Test public void testDefaultRabbitConfiguration(){
  load(TestConfiguration.class);
  RabbitTemplate rabbitTemplate=this.context.getBean(RabbitTemplate.class);
  RabbitMessagingTemplate messagingTemplate=this.context.getBean(RabbitMessagingTemplate.class);
  CachingConnectionFactory connectionFactory=this.context.getBean(CachingConnectionFactory.class);
  RabbitAdmin amqpAdmin=this.context.getBean(RabbitAdmin.class);
  assertEquals(connectionFactory,rabbitTemplate.getConnectionFactory());
  assertEquals(rabbitTemplate,messagingTemplate.getRabbitTemplate());
  assertNotNull(amqpAdmin);
  assertEquals("localhost",connectionFactory.getHost());
  assertTrue("Listener container factory should be created by default",this.context.containsBean("rabbitListenerContainerFactory"));
}
