@Test public void should_create_new_folder(){
  File f=null;
  try {
    f=Files.newFolder("folder");
    assertTrue(f.isDirectory());
  }
  finally {
    if (f != null)     f.delete();
  }
}
