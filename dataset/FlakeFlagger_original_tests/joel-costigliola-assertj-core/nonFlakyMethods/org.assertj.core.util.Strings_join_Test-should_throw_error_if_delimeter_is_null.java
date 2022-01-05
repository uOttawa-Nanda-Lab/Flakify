@Test public void should_throw_error_if_delimeter_is_null(){
  thrown.expect(IllegalArgumentException.class);
  Strings.join(null,"Uno","Dos").with(null);
}
