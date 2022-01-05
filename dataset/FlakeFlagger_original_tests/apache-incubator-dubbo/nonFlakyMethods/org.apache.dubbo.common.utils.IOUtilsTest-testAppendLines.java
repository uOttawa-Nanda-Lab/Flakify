@Test public void testAppendLines() throws Exception {
  File file=tmpDir.newFile();
  IOUtils.appendLines(file,new String[]{"a","b","c"});
  String[] lines=IOUtils.readLines(file);
  assertThat(lines.length,equalTo(3));
  assertThat(lines[0],equalTo("a"));
  assertThat(lines[1],equalTo("b"));
  assertThat(lines[2],equalTo("c"));
}
