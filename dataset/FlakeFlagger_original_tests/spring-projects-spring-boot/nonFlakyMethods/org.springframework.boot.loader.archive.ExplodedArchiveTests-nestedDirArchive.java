@Test public void nestedDirArchive() throws Exception {
  Entry entry=getEntriesMap(this.archive).get("d/");
  Archive nested=this.archive.getNestedArchive(entry);
  Map<String,Entry> nestedEntries=getEntriesMap(nested);
  assertThat(nestedEntries.size(),equalTo(1));
  assertThat(nested.getUrl().toString(),equalTo("file:" + this.rootFolder.toURI().getPath() + "d/"));
}
