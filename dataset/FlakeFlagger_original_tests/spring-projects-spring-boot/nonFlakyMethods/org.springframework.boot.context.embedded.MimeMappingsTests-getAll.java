@Test public void getAll() throws Exception {
  MimeMappings mappings=new MimeMappings();
  mappings.add("foo","bar");
  mappings.add("baz","boo");
  List<MimeMappings.Mapping> mappingList=new ArrayList<MimeMappings.Mapping>();
  mappingList.addAll(mappings.getAll());
  assertThat(mappingList.get(0).getExtension(),equalTo("foo"));
  assertThat(mappingList.get(0).getMimeType(),equalTo("bar"));
  assertThat(mappingList.get(1).getExtension(),equalTo("baz"));
  assertThat(mappingList.get(1).getMimeType(),equalTo("boo"));
}
