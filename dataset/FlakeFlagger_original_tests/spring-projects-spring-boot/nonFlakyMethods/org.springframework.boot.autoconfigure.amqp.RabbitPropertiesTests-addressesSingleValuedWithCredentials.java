@Test public void addressesSingleValuedWithCredentials(){
  this.properties.setAddresses("amqp://root:password@otherhost:1111/host");
  assertEquals("otherhost",this.properties.getHost());
  assertEquals(1111,this.properties.getPort());
  assertEquals("root",this.properties.getUsername());
  assertEquals("host",this.properties.getVirtualHost());
}
