@Test public void testHashCode() throws Exception {
  Protocol protocol0=new Protocol("");
  Protocol protocol1=new Protocol("protocol");
  Protocol protocol2=new Protocol("protocol");
  assertNotEquals(protocol0,protocol1);
  assertNotEquals(protocol0,protocol2);
  assertEquals(protocol1,protocol2);
}
