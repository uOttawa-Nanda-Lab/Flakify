@Test public void testReadShort() throws IOException {
  fastJsonObjectInput=new FastJsonObjectInput(new StringReader("1"));
  short result=fastJsonObjectInput.readShort();
  assertThat(result,is((short)1));
}
