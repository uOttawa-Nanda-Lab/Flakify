@Test public void readAndWriteUtf8() throws Exception {
  OkBuffer buffer=new OkBuffer();
  buffer.writeUtf8("ab");
  assertEquals(2,buffer.size());
  buffer.writeUtf8("cdef");
  assertEquals(6,buffer.size());
  assertEquals("abcd",buffer.readUtf8(4));
  assertEquals(2,buffer.size());
  assertEquals("ef",buffer.readUtf8(2));
  assertEquals(0,buffer.size());
  try {
    buffer.readUtf8(1);
    fail();
  }
 catch (  ArrayIndexOutOfBoundsException expected) {
  }
}
