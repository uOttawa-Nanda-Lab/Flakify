@Test public void testLines() throws Exception {
  File file=tmpDir.newFile();
  IOUtils.writeLines(file,new String[]{TEXT});
  String[] lines=IOUtils.readLines(file);
  assertThat(lines.length,equalTo(1));
  assertThat(lines[0],equalTo(TEXT));
}
