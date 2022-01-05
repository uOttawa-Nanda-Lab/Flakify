@Test(expected=IllegalStateException.class) public void doNotFindGetterWithArgument() throws Exception {
  ReflectionUtils.getGetter(Foo.class,"c");
  fail("Should have thrown exception - getC is not a getter");
}
