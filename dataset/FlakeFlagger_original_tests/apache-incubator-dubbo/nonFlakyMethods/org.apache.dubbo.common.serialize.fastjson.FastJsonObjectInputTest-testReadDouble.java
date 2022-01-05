@Test public void testReadDouble() throws IOException {
  fastJsonObjectInput=new FastJsonObjectInput(new StringReader("1.88"));
  Double result=fastJsonObjectInput.readDouble();
  assertThat(result,is(1.88d));
}
