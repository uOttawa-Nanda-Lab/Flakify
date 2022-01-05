@Test public void should_throw_error_if_value_is_negative(){
  thrown.expectIllegalArgumentException("The value of the index should not be negative");
  Index.atIndex(-1);
}
