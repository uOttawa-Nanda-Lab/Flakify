@Test public void testToString() throws Exception {
  Protocol protocol0=new Protocol("");
  assertEquals(protocol0.getProvidedProtocol(),"");
  Protocol protocol1=new Protocol("protocol");
  assertEquals(protocol1.getProvidedProtocol(),"protocol");
}
