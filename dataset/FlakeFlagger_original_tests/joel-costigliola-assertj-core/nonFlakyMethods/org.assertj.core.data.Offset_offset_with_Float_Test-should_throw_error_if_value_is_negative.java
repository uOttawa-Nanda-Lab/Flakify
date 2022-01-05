@Test public void should_throw_error_if_value_is_negative(){
  thrown.expectIllegalArgumentException(offsetValueIsNotPositive());
  Offset.offset(-1f);
}
