@Test public void testCustomFalsyVirtualHost(){
  this.properties.setVirtualHost("/myvHost");
  assertEquals("/myvHost",this.properties.getVirtualHost());
}
