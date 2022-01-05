@Test public void createFromExisting() throws Exception {
  MimeMappings mappings=new MimeMappings();
  mappings.add("foo","bar");
  MimeMappings clone=new MimeMappings(mappings);
  mappings.add("baz","bar");
  assertThat(clone.get("foo"),equalTo("bar"));
  assertThat(clone.get("baz"),nullValue());
}
