@Test public void addressesDoubleValuedWithCredentials(){
  this.properties.setAddresses("myhost:9999,root:password@otherhost:1111/host");
  assertNull(this.properties.getHost());
  assertEquals(9999,this.properties.getPort());
  assertEquals("root",this.properties.getUsername());
  assertEquals("host",this.properties.getVirtualHost());
}
