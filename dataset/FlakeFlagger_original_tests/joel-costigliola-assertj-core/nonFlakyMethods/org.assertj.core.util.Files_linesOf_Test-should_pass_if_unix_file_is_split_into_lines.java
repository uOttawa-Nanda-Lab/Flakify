@Test public void should_pass_if_unix_file_is_split_into_lines(){
  assertThat(linesOf(SAMPLE_UNIX_FILE,Charset.forName(UTF_8))).isEqualTo(EXPECTED_CONTENT);
}
