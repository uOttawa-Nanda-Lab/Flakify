@Test public void get() throws Exception {
  MimeMappings mappings=new MimeMappings();
  mappings.add("foo","bar");
  assertThat(mappings.get("foo"),equalTo("bar"));
}
