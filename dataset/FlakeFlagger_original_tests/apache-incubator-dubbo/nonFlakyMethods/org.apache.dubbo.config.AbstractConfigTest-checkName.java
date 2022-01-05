@Test(expected=IllegalStateException.class) public void checkName() throws Exception {
  AbstractConfig.checkName("hello","world%");
}
