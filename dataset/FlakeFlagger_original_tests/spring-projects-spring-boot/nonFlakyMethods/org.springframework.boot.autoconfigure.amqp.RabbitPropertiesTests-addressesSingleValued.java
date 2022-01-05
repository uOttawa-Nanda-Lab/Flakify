@Test public void addressesSingleValued(){
  this.properties.setAddresses("myhost:9999");
  assertEquals("myhost",this.properties.getHost());
  assertEquals(9999,this.properties.getPort());
}
