@Test public void testRabbitTemplateBackOff(){
  load(TestConfiguration3.class);
  RabbitTemplate rabbitTemplate=this.context.getBean(RabbitTemplate.class);
  assertEquals(this.context.getBean("testMessageConverter"),rabbitTemplate.getMessageConverter());
}
