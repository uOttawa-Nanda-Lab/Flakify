@Test public void testPubSubDisabledByDefault(){
  load(TestConfiguration.class);
  JmsTemplate jmsTemplate=this.context.getBean(JmsTemplate.class);
  assertFalse(jmsTemplate.isPubSubDomain());
}
