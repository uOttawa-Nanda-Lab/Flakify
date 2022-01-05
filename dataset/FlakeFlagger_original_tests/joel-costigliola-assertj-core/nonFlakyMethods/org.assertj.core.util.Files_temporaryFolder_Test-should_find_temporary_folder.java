@Test public void should_find_temporary_folder(){
  File temporaryFolder=Files.temporaryFolder();
  assertTrue(temporaryFolder.isDirectory());
  String a=append(separator).to(temporaryFolder.getAbsolutePath());
  String e=append(separator).to(systemTemporaryFolder());
  assertEquals(e,a);
}
