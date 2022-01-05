@Test public void testReadBytes() throws IOException {
  fastJsonObjectInput=new FastJsonObjectInput(new ByteArrayInputStream("123456".getBytes()));
  byte[] result=fastJsonObjectInput.readBytes();
  assertThat(result,is("123456".getBytes()));
}
