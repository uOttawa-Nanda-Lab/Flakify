@Test(expected=IllegalStateException.class) public void checkLength() throws Exception {
  StringBuilder builder=new StringBuilder();
  for (int i=0; i <= 200; i++) {
    builder.append("a");
  }
  AbstractConfig.checkLength("hello",builder.toString());
}
