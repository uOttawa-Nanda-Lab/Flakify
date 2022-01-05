@Test public void getFilteredArchive() throws Exception {
  Archive filteredArchive=this.archive.getFilteredArchive(new Archive.EntryRenameFilter(){
    @Override public AsciiBytes apply(    AsciiBytes entryName,    Entry entry){
      if (entryName.toString().equals("1.dat")) {
        return entryName;
      }
      return null;
    }
  }
);
  Map<String,Entry> entries=getEntriesMap(filteredArchive);
  assertThat(entries.size(),equalTo(1));
}
