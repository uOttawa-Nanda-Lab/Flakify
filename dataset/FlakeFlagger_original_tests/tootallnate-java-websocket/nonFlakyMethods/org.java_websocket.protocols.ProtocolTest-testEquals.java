@Test public void testEquals() throws Exception {
  Protocol protocol0=new Protocol("");
  Protocol protocol1=new Protocol("protocol");
  Protocol protocol2=new Protocol("protocol");
  assertTrue(!protocol0.equals(protocol1));
  assertTrue(!protocol0.equals(protocol2));
  assertTrue(protocol1.equals(protocol2));
}
