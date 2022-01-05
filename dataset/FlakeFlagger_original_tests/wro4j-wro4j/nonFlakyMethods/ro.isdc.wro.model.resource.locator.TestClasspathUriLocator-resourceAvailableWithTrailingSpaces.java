@Test public void resourceAvailableWithTrailingSpaces() throws IOException {
  Assert.assertNotNull(uriLocator.locate(createUri(" test.css ")));
}
