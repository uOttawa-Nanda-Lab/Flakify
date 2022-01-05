@Test public void should_throw_IllegalArgumentException_if_string_is_empty(){
  thrown.expectIllegalArgumentException(Preconditions.ARGUMENT_EMPTY);
  String string="";
  Preconditions.checkNotNullOrEmpty(string);
}
