@Test public void testReadBool() throws IOException {
  fastJsonObjectInput=new FastJsonObjectInput(new ByteArrayInputStream("true".getBytes()));
  boolean result=fastJsonObjectInput.readBool();
  assertThat(result,is(true));
  fastJsonObjectInput=new FastJsonObjectInput(new StringReader("false"));
  result=fastJsonObjectInput.readBool();
  assertThat(result,is(false));
}
