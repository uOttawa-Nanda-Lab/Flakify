@Test public void remove() throws Exception {
  MimeMappings mappings=new MimeMappings();
  mappings.add("foo","bar");
  assertThat(mappings.remove("foo"),equalTo("bar"));
  assertThat(mappings.remove("foo"),nullValue());
}
