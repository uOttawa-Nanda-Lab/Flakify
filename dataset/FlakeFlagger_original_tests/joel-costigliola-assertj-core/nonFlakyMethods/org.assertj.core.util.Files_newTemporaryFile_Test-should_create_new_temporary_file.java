@Test public void should_create_new_temporary_file(){
  File f=null;
  try {
    f=Files.newTemporaryFile();
    assertTrue(f.isFile());
  }
  finally {
    if (f != null)     f.delete();
  }
}
