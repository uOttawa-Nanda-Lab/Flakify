@Test public void genericGet(){
  cyclicBufferAppender.append("Some string");
  String foo=cyclicBufferAppender.get(0);
  assertEquals("Some string",foo);
}
