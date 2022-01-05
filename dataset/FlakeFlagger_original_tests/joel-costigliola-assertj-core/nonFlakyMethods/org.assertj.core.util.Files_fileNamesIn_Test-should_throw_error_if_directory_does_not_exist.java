@Test public void should_throw_error_if_directory_does_not_exist(){
  String path=concat("root",separator,"not_existing_dir");
  thrown.expect(IllegalArgumentException.class);
  Files.fileNamesIn(path,false);
}
