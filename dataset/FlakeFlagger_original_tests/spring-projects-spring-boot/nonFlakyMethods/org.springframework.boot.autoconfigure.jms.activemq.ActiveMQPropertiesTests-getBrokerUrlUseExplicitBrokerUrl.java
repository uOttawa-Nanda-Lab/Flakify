@Test public void getBrokerUrlUseExplicitBrokerUrl(){
  this.properties.setBrokerUrl("vm://foo-bar");
  assertEquals("vm://foo-bar",new ActiveMQConnectionFactoryFactory(this.properties).determineBrokerUrl());
}
