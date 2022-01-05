@Test public void should_fail_when_a_field_differ(){
  AssertionInfo info=someInfo();
  Jedi actual=new Jedi("Yoda","Green");
  Jedi other=new Jedi("Soda","Green");
  try {
    objects.assertIsLenientEqualsToIgnoringNullFields(info,actual,other);
  }
 catch (  AssertionError err) {
    verify(failures).failure(info,shouldBeEqualToIgnoringGivenFields(actual,newArrayList("name"),newArrayList((Object)"Yoda"),newArrayList((Object)"Soda"),newArrayList("strangeNotReadablePrivateField")));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
