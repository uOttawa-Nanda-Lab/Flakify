@Test public void testJmsMessagingTemplateBackOff(){
  load(TestConfiguration5.class);
  JmsMessagingTemplate messagingTemplate=this.context.getBean(JmsMessagingTemplate.class);
  assertEquals("fooBar",messagingTemplate.getDefaultDestinationName());
}
