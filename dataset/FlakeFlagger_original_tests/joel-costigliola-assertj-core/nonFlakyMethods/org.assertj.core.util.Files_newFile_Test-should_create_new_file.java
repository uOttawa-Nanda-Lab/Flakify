@Test public void should_create_new_file(){
  File f=null;
  try {
    f=Files.newFile("file");
    assertTrue(f.isFile());
  }
  finally {
    if (f != null)     f.delete();
  }
}
