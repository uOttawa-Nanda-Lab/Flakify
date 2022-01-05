@Test public void addressesSingleValuedWithCredentialsDefaultPort(){
  this.properties.setAddresses("amqp://root:password@lemur.cloudamqp.com/host");
  assertEquals("lemur.cloudamqp.com",this.properties.getHost());
  assertEquals(5672,this.properties.getPort());
  assertEquals("root",this.properties.getUsername());
  assertEquals("host",this.properties.getVirtualHost());
  assertEquals("lemur.cloudamqp.com:5672",this.properties.getAddresses());
}
