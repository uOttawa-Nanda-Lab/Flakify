@Test public void should_fail_if_actual_is_not_assignable_from(){
  AssertionInfo info=someInfo();
  actual=HumanJedi.class;
  Class<?>[] expected=new Class[]{HumanJedi.class,Jedi.class};
  try {
    classes.assertIsAssignableFrom(someInfo(),actual,expected);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldBeAssignableFrom(actual,Sets.<Class<?>>newLinkedHashSet(expected),Sets.<Class<?>>newLinkedHashSet(Jedi.class)));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
