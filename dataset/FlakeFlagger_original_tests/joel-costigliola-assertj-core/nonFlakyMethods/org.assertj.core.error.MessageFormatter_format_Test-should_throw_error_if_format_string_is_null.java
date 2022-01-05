@Test public void should_throw_error_if_format_string_is_null(){
  thrown.expect(NullPointerException.class);
  messageFormatter.format(null,null,null);
}
