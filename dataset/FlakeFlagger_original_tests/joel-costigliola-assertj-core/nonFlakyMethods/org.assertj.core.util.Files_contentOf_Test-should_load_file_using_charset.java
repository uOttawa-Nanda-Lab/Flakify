@Test public void should_load_file_using_charset(){
  assertEquals(expectedContent,Files.contentOf(sampleFile,Charset.forName("UTF-8")));
}
