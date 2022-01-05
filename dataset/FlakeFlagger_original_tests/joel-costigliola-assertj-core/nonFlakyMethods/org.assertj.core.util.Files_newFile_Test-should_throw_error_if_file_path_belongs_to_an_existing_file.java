@Test public void should_throw_error_if_file_path_belongs_to_an_existing_file(){
  String path=join("root","dir_1","file_1_1").with(separator);
  thrown.expect(FilesException.class);
  Files.newFile(path);
}
