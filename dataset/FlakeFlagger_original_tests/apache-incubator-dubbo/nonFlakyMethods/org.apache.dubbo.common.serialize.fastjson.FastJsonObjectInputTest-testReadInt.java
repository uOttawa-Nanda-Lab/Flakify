@Test public void testReadInt() throws IOException {
  fastJsonObjectInput=new FastJsonObjectInput(new StringReader("1"));
  Integer result=fastJsonObjectInput.readInt();
  assertThat(result,is(1));
}
