@Test public void testLocateJarStream() throws IOException {
  final InputStream is=jarStreamLocator.locateStream("com/test/app/*.js",new File(jarFileName));
  final List<String> lines=IOUtils.readLines(is);
  LOG.debug("lines: " + lines);
  assertEquals(testInfo,lines.get(0));
  IOUtils.closeQuietly(is);
}
