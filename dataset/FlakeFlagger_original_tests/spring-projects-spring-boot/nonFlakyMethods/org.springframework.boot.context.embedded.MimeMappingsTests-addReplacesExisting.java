@Test public void addReplacesExisting() throws Exception {
  MimeMappings mappings=new MimeMappings();
  mappings.add("foo","bar");
  assertThat(mappings.add("foo","baz"),equalTo("bar"));
}
