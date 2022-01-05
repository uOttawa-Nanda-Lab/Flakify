@Test public void addressesDoubleValuedPreservesOrder(){
  this.properties.setAddresses("myhost:9999,ahost:1111/host");
  assertNull(this.properties.getHost());
  assertEquals("myhost:9999,ahost:1111",this.properties.getAddresses());
}
