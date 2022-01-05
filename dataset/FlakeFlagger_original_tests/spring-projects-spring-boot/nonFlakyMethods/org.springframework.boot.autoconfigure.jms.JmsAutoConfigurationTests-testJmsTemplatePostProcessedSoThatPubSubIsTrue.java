@Test public void testJmsTemplatePostProcessedSoThatPubSubIsTrue(){
  load(TestConfiguration4.class);
  JmsTemplate jmsTemplate=this.context.getBean(JmsTemplate.class);
  assertTrue(jmsTemplate.isPubSubDomain());
}
