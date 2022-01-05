@Test public void assertRegisterTypeAdapter(){
  Gson beforeRegisterGson=GsonFactory.getGson();
  GsonFactory.registerTypeAdapter(GsonFactoryTest.class,new TypeAdapter(){
    @Override public Object read(    final JsonReader in) throws IOException {
      return null;
    }
    @Override public void write(    final JsonWriter out,    final Object value) throws IOException {
      out.jsonValue("test");
    }
  }
);
  assertThat(beforeRegisterGson.toJson(new GsonFactoryTest()),is("{}"));
  assertThat(GsonFactory.getGson().toJson(new GsonFactoryTest()),is("test"));
}
