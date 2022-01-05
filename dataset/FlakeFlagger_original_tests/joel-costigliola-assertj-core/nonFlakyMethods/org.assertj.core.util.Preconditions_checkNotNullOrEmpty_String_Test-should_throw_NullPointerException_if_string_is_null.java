@Test public void should_throw_NullPointerException_if_string_is_null(){
  thrown.expect(NullPointerException.class);
  String string=null;
  Preconditions.checkNotNullOrEmpty(string);
}
