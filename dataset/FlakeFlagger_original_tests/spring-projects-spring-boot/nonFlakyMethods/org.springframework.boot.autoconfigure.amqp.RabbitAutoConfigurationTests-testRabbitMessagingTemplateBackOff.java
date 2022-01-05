@Test public void testRabbitMessagingTemplateBackOff(){
  load(TestConfiguration4.class);
  RabbitMessagingTemplate messagingTemplate=this.context.getBean(RabbitMessagingTemplate.class);
  assertEquals("fooBar",messagingTemplate.getDefaultDestination());
}
