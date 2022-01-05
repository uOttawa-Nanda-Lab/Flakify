@Test public void should_fail_when_objects_to_compare_are_of_different_types(){
  AssertionInfo info=someInfo();
  Jedi actual=new Jedi("Yoda","Green");
  Employee other=new Employee();
  try {
    objects.assertIsEqualToComparingOnlyGivenFields(info,actual,other,"name");
  }
 catch (  AssertionError err) {
    verify(failures).failure(info,shouldBeInstance(other,actual.getClass()));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
