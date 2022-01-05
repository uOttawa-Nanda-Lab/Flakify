@Test public void should_fail_if_actual_is_empty(){
  thrown.expectAssertionError(actualIsEmpty());
  List<String> empty=emptyList();
  lists.assertIs(someInfo(),empty,condition,someIndex());
}
