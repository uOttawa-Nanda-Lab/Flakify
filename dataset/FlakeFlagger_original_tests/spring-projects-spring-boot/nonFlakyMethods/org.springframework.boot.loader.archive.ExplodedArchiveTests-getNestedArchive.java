@Test public void getNestedArchive() throws Exception {
  Entry entry=getEntriesMap(this.archive).get("nested.jar");
  Archive nested=this.archive.getNestedArchive(entry);
  assertThat(nested.getUrl().toString(),equalTo("jar:" + this.rootFolder.toURI() + "nested.jar!/"));
}
