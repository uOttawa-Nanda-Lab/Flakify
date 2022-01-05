@Test public void testMultipleCharsets(){
  try {
    MediaType.parse("text/plain; charset=utf-8; charset=utf-16");
    fail();
  }
 catch (  IllegalArgumentException expected) {
  }
}
