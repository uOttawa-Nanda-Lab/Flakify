@Test public void testCustomVirtualHost(){
  this.properties.setVirtualHost("myvHost");
  assertEquals("myvHost",this.properties.getVirtualHost());
}
