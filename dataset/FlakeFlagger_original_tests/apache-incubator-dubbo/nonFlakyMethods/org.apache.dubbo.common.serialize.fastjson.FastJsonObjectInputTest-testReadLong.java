@Test public void testReadLong() throws IOException {
  fastJsonObjectInput=new FastJsonObjectInput(new StringReader("10"));
  Long result=fastJsonObjectInput.readLong();
  assertThat(result,is(10L));
}
