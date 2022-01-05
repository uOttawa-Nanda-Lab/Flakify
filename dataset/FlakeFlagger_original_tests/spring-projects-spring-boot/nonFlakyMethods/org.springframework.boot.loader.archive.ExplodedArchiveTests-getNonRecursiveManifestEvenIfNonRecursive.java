@Test public void getNonRecursiveManifestEvenIfNonRecursive() throws Exception {
  ExplodedArchive archive=new ExplodedArchive(new File("src/test/resources/root"),false);
  assertNotNull(archive.getManifest());
  Map<String,Archive.Entry> entries=getEntriesMap(archive);
  assertThat(entries.size(),equalTo(3));
}
