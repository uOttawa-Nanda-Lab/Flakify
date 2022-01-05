@Test public void configurationBacksOffWhenCustomConnectionFactoryExists(){
  load(CustomConnectionFactoryConfiguration.class);
  assertTrue(mockingDetails(this.context.getBean(ConnectionFactory.class)).isMock());
}
