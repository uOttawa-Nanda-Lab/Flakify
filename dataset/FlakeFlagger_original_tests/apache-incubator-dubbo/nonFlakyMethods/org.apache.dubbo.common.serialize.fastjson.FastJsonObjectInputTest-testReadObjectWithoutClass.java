@Test public void testReadObjectWithoutClass() throws IOException, ClassNotFoundException {
  fastJsonObjectInput=new FastJsonObjectInput(new StringReader("{ \"name\":\"John\", \"age\":30 }"));
  JSONObject readObject=(JSONObject)fastJsonObjectInput.readObject();
  assertThat(readObject,not(nullValue()));
  assertThat(readObject.getString("name"),is("John"));
  assertThat(readObject.getInteger("age"),is(30));
}
