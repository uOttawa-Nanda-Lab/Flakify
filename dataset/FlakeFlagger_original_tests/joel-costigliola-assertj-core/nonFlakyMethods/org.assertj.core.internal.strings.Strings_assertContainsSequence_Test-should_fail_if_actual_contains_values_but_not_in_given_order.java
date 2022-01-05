@Test public void should_fail_if_actual_contains_values_but_not_in_given_order(){
  AssertionInfo info=someInfo();
  String[] sequenceValues={"{","author","A Game of Thrones","}"};
  String actual="{ 'title':'A Game of Thrones', 'author':'George Martin'}";
  try {
    strings.assertContainsSequence(info,actual,sequenceValues);
  }
 catch (  AssertionError e) {
    verify(failures).failure(info,shouldContainSequence(actual,sequenceValues,1));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
