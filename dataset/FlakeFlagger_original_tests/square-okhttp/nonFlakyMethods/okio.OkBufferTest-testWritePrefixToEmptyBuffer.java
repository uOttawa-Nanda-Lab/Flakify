@Test public void testWritePrefixToEmptyBuffer(){
  OkBuffer sink=new OkBuffer();
  OkBuffer source=new OkBuffer();
  source.writeUtf8("abcd");
  sink.write(source,2);
  assertEquals("ab",sink.readUtf8(2));
}
