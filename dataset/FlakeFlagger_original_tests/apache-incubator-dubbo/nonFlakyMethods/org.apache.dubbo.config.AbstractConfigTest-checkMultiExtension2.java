@Test(expected=IllegalStateException.class) public void checkMultiExtension2() throws Exception {
  AbstractConfig.checkMultiExtension(Greeting.class,"hello","default,-world");
}
