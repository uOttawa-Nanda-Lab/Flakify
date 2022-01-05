@Test public void addressesDoubleValued(){
  this.properties.setAddresses("myhost:9999,otherhost:1111");
  assertNull(this.properties.getHost());
  assertEquals(9999,this.properties.getPort());
}
