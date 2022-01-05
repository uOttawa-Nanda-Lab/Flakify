@Test public void getEntries() throws Exception {
  Map<String,Archive.Entry> entries=getEntriesMap(this.archive);
  assertThat(entries.size(),equalTo(9));
}
