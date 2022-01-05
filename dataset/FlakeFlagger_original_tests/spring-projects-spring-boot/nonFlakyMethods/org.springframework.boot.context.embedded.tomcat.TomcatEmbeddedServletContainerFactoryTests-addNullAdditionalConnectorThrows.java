@Test public void addNullAdditionalConnectorThrows(){
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Connectors must not be null");
  factory.addAdditionalTomcatConnectors((Connector[])null);
}
