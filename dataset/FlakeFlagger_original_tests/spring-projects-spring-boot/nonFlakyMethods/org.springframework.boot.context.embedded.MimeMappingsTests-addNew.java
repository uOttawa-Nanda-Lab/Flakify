@Test public void addNew() throws Exception {
  MimeMappings mappings=new MimeMappings();
  assertThat(mappings.add("foo","bar"),nullValue());
}
