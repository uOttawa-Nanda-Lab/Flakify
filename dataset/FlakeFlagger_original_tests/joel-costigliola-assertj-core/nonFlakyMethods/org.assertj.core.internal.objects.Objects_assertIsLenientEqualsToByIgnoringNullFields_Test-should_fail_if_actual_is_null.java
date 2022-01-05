@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  Jedi other=new Jedi("Yoda","Green");
  objects.assertIsLenientEqualsToIgnoringNullFields(someInfo(),null,other);
}
