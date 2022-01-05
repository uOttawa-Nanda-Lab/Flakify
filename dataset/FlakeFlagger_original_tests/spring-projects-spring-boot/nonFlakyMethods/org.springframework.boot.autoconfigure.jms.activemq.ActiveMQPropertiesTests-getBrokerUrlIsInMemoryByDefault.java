@Test public void getBrokerUrlIsInMemoryByDefault(){
  assertEquals(DEFAULT_EMBEDDED_BROKER_URL,new ActiveMQConnectionFactoryFactory(this.properties).determineBrokerUrl());
}
