@Test public void testReadLines() throws Exception {
  String[] lines=IOUtils.readLines(is);
  assertThat(lines.length,equalTo(1));
  assertThat(lines[0],equalTo(TEXT));
}
