@Test public void testConstructor() throws Exception {
  Protocol protocol0=new Protocol("");
  try {
    Protocol protocol1=new Protocol(null);
    fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException e) {
  }
}
