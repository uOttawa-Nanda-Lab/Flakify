@Test(expected=IllegalStateException.class) public void checkPathLength() throws Exception {
  StringBuilder builder=new StringBuilder();
  for (int i=0; i <= 200; i++) {
    builder.append("a");
  }
  AbstractConfig.checkPathLength("hello",builder.toString());
}
