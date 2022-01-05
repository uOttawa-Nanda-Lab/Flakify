@Test public void testemptyVirtualHost(){
  this.properties.setVirtualHost("");
  assertEquals("/",this.properties.getVirtualHost());
}
