@Test public void testReadObject() throws IOException, ClassNotFoundException {
  fastJsonObjectInput=new FastJsonObjectInput(new StringReader("{ \"name\":\"John\", \"age\":30 }"));
  Person result=fastJsonObjectInput.readObject(Person.class);
  assertThat(result,not(nullValue()));
  assertThat(result.getName(),is("John"));
  assertThat(result.getAge(),is(30));
}
