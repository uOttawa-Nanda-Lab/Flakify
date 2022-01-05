@Test public void getNonRecursiveManifest() throws Exception {
  ExplodedArchive archive=new ExplodedArchive(new File("src/test/resources/root"));
  assertNotNull(archive.getManifest());
  Map<String,Archive.Entry> entries=getEntriesMap(archive);
  assertThat(entries.size(),equalTo(4));
}
