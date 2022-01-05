@Test public void getBrokerUrlWithInMemorySetToFalse(){
  this.properties.setInMemory(false);
  assertEquals(DEFAULT_NETWORK_BROKER_URL,new ActiveMQConnectionFactoryFactory(this.properties).determineBrokerUrl());
}
