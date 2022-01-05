@Test public void bug10100() throws Exception {
  assertEquals("image/jpeg",URLConnection.guessContentTypeFromName("someFile.jpg"));
  assertEquals("application/pdf",URLConnection.guessContentTypeFromName("stuff.pdf"));
}
