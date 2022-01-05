@Test public void makeUnmodifiable() throws Exception {
  MimeMappings mappings=new MimeMappings();
  mappings.add("foo","bar");
  MimeMappings unmodifiable=MimeMappings.unmodifiableMappings(mappings);
  try {
    unmodifiable.remove("foo");
  }
 catch (  UnsupportedOperationException ex) {
  }
  mappings.remove("foo");
  assertThat(unmodifiable.get("foo"),nullValue());
}
