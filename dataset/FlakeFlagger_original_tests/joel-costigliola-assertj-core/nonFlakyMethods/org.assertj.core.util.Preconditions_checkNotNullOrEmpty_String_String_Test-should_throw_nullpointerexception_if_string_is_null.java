@Test public void should_throw_nullpointerexception_if_string_is_null(){
  thrown.expect(NullPointerException.class);
  String string=null;
  Preconditions.checkNotNullOrEmpty(string,CUSTOM_MESSAGE);
}
