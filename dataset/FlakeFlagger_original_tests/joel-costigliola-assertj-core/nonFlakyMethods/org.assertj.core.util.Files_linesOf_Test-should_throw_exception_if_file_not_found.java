@Test public void should_throw_exception_if_file_not_found(){
  File missingFile=new File("missing.txt");
  assertThat(missingFile).doesNotExist();
  thrown.expect(FilesException.class);
  linesOf(missingFile,Charset.defaultCharset());
}
