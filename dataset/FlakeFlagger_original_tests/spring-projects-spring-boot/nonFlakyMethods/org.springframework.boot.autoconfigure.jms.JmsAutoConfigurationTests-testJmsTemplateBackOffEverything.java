@Test public void testJmsTemplateBackOffEverything(){
  this.context=createContext(TestConfiguration2.class,TestConfiguration3.class,TestConfiguration5.class);
  JmsTemplate jmsTemplate=this.context.getBean(JmsTemplate.class);
  assertEquals(999,jmsTemplate.getPriority());
  assertEquals("foobar",this.context.getBean(ActiveMQConnectionFactory.class).getBrokerURL());
  JmsMessagingTemplate messagingTemplate=this.context.getBean(JmsMessagingTemplate.class);
  assertEquals("fooBar",messagingTemplate.getDefaultDestinationName());
  assertEquals(jmsTemplate,messagingTemplate.getJmsTemplate());
}
