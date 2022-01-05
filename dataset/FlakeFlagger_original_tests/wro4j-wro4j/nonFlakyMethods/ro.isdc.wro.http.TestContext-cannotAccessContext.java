@Test(expected=WroRuntimeException.class) public void cannotAccessContext(){
  Context.unset();
  Context.get();
}
