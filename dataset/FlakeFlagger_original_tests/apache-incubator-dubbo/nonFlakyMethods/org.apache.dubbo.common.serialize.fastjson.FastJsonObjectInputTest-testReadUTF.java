@Test public void testReadUTF() throws IOException {
  fastJsonObjectInput=new FastJsonObjectInput(new StringReader("\"wording\""));
  String result=fastJsonObjectInput.readUTF();
  assertThat(result,is("wording"));
}
