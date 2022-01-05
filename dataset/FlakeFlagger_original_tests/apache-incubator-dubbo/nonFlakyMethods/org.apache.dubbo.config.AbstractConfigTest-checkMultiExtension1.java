@Test(expected=IllegalStateException.class) public void checkMultiExtension1() throws Exception {
  AbstractConfig.checkMultiExtension(Greeting.class,"hello","default,world");
}
