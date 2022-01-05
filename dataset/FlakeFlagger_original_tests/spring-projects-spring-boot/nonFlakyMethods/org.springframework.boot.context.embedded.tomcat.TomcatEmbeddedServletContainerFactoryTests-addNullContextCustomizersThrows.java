@Test public void addNullContextCustomizersThrows(){
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("TomcatContextCustomizers must not be null");
  factory.addContextCustomizers((TomcatContextCustomizer[])null);
}
