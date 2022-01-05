@Test public void should_load_file_using_charset_name(){
  assertEquals(expectedContent,Files.contentOf(sampleFile,"UTF-8"));
}
