@Test(expected=WroRuntimeException.class) public void cannotInjectOutsideOfContext(){
  Context.unset();
  victim.inject(new TestProcessor());
}
