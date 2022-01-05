@Test public void shouldIncludeMessageWhenFailing(){
  String message="Failed :(";
  thrown.expectAssertionError(message);
  Fail.fail(message);
}
