@Test public void testJmsTemplateBackOff(){
  load(TestConfiguration3.class);
  JmsTemplate jmsTemplate=this.context.getBean(JmsTemplate.class);
  assertEquals(999,jmsTemplate.getPriority());
}
