@Test(expected=WroRuntimeException.class) public void canInjectContextOutsideOfContextScope() throws Exception {
  Context.unset();
  shouldInjectContext();
}
