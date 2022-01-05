@Test public void getExplicitBrokerUrlAlwaysWins(){
  this.properties.setBrokerUrl("vm://foo-bar");
  this.properties.setInMemory(false);
  assertEquals("vm://foo-bar",new ActiveMQConnectionFactoryFactory(this.properties).determineBrokerUrl());
}
