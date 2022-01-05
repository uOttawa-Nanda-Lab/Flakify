@Test public void should_throw_error_if_value_is_null(){
  thrown.expect(NullPointerException.class);
  Double value=null;
  Offset.offset(value);
}
