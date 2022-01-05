@Test public void should_return_current_folder() throws IOException {
  File e=new File(".");
  File a=Files.currentFolder();
  assertEquals(e.getCanonicalPath(),a.getCanonicalPath());
}
