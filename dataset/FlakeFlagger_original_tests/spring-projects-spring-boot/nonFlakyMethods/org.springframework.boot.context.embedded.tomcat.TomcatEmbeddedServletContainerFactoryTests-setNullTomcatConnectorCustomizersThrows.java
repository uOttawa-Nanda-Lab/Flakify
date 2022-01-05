@Test public void setNullTomcatConnectorCustomizersThrows(){
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("TomcatConnectorCustomizers must not be null");
  factory.setTomcatConnectorCustomizers(null);
}
