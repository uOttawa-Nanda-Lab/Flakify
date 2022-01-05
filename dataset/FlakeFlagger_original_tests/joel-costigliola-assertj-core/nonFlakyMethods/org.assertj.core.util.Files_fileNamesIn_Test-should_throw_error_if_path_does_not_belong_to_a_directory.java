@Test public void should_throw_error_if_path_does_not_belong_to_a_directory() throws Exception {
  String fileName="file_1";
  root.addFiles(fileName);
  String path=concat("root",separator,fileName);
  thrown.expect(IllegalArgumentException.class);
  Files.fileNamesIn(path,false);
}
