@Test public void testReadFloat() throws IOException {
  fastJsonObjectInput=new FastJsonObjectInput(new StringReader("1.66"));
  Float result=fastJsonObjectInput.readFloat();
  assertThat(result,is(1.66F));
}
