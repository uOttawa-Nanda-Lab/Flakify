@Test(expected=UnsupportedOperationException.class) public void defaultsCannotBeModified() throws Exception {
  MimeMappings.DEFAULT.add("foo","foo/bar");
}
