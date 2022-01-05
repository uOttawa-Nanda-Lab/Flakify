@Test public void should_read_lines_of_file_with_UTF8_charset(){
  File file=new File("src/test/resources/utf8.txt");
  assertThat(linesOf(file,UTF_8)).isEqualTo(EXPECTED_CONTENT);
  assertThat(linesOf(file,Charset.forName(UTF_8))).isEqualTo(EXPECTED_CONTENT);
}
