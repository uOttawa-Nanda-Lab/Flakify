@Test public void addressesNotSet(){
  assertEquals("localhost",this.properties.getHost());
  assertEquals(5672,this.properties.getPort());
}
