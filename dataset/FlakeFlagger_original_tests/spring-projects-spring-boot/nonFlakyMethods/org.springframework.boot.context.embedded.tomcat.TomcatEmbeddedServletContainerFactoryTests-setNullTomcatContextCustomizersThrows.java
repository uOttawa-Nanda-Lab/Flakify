@Test public void setNullTomcatContextCustomizersThrows(){
  TomcatEmbeddedServletContainerFactory factory=getFactory();
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("TomcatContextCustomizers must not be null");
  factory.setTomcatContextCustomizers(null);
}
