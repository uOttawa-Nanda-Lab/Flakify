@Test(expected=IOException.class) public void resourceUnavailable() throws Exception {
  uriLocator.locate(createUri("123123.css"));
}
