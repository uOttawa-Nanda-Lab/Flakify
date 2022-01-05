@Test public void createFromMap() throws Exception {
  Map<String,String> mappings=new HashMap<String,String>();
  mappings.put("foo","bar");
  MimeMappings clone=new MimeMappings(mappings);
  mappings.put("baz","bar");
  assertThat(clone.get("foo"),equalTo("bar"));
  assertThat(clone.get("baz"),nullValue());
}
