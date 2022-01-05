@Test public void getMissing() throws Exception {
  MimeMappings mappings=new MimeMappings();
  assertThat(mappings.get("foo"),nullValue());
}
