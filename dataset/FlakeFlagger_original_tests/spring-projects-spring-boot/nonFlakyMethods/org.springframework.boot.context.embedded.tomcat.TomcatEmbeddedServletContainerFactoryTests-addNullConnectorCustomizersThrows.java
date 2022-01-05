@Test public void addNullConnectorCustomizersThrows(){
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("TomcatConnectorCustomizers must not be null");
  factory.addConnectorCustomizers((TomcatConnectorCustomizer[])null);
}
