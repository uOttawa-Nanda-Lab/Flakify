@Test public void testDefaultVirtualHost(){
  this.properties.setVirtualHost("/");
  assertEquals("/",this.properties.getVirtualHost());
}
