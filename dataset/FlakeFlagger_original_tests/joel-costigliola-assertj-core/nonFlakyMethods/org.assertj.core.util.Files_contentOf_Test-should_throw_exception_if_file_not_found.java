@Test public void should_throw_exception_if_file_not_found(){
  File missingFile=new File("missing.txt");
  assertFalse(missingFile.exists());
  thrown.expect(FilesException.class);
  Files.contentOf(missingFile,Charset.defaultCharset());
}
