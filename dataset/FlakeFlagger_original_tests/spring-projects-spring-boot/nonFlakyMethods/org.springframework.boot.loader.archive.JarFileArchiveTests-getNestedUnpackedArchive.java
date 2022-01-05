@Test public void getNestedUnpackedArchive() throws Exception {
  setup(true);
  Entry entry=getEntriesMap(this.archive).get("nested.jar");
  Archive nested=this.archive.getNestedArchive(entry);
  assertThat(nested.getUrl().toString(),startsWith("file:"));
  assertThat(nested.getUrl().toString(),endsWith(".jar"));
}
