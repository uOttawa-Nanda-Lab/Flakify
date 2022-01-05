@Test public void testReadByte() throws IOException {
  fastJsonObjectInput=new FastJsonObjectInput(new ByteArrayInputStream("123".getBytes()));
  Byte result=fastJsonObjectInput.readByte();
  assertThat(result,is(Byte.parseByte("123")));
}
