@Test public void should_fail_when_some_inherited_field_values_differ(){
  AssertionInfo info=someInfo();
  Jedi actual=new Jedi("Yoda","Green");
  Jedi other=new Jedi("Luke","Green");
  try {
    objects.assertIsEqualToIgnoringGivenFields(info,actual,other,"lightSaberColor");
  }
 catch (  AssertionError err) {
    verify(failures).failure(info,shouldBeEqualToIgnoringGivenFields(actual,newArrayList("name"),newArrayList((Object)"Yoda"),newArrayList((Object)"Luke"),newArrayList("lightSaberColor")));
    return;
  }
  failBecauseExpectedAssertionErrorWasNotThrown();
}
