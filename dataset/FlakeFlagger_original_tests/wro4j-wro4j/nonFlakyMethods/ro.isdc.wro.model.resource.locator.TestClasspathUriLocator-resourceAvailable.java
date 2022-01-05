@Test public void resourceAvailable() throws IOException {
  Assert.assertNotNull(uriLocator.locate(createUri("test.css")));
}
