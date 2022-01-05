@Test public void should_throw_error_if_file_path_belongs_to_directory_that_is_not_empty(){
  thrown.expect(FilesException.class);
  Files.newFile("root");
}
