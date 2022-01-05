@Test public void should_throw_error_if_sequence_is_null(){
  thrown.expectNullPointerException(charSequenceToLookForIsNull());
  strings.assertContains(someInfo(),"Yoda",(String)null);
}
