@Test public void should_throw_illegalargumentexception_if_string_is_empty(){
  thrown.expectIllegalArgumentException(CUSTOM_MESSAGE);
  String string="";
  Preconditions.checkNotNullOrEmpty(string,CUSTOM_MESSAGE);
}
