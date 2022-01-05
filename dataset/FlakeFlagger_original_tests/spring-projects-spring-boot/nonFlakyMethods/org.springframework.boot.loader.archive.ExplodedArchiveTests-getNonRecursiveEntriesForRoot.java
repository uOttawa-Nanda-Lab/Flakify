@Test public void getNonRecursiveEntriesForRoot() throws Exception {
  ExplodedArchive archive=new ExplodedArchive(new File("/"),false);
  Map<String,Archive.Entry> entries=getEntriesMap(archive);
  assertThat(entries.size(),greaterThan(1));
}
