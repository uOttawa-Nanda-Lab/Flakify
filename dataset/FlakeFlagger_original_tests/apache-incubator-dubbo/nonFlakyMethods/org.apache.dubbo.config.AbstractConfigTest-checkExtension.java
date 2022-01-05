@Test(expected=IllegalStateException.class) public void checkExtension() throws Exception {
  AbstractConfig.checkExtension(Greeting.class,"hello","world");
}
