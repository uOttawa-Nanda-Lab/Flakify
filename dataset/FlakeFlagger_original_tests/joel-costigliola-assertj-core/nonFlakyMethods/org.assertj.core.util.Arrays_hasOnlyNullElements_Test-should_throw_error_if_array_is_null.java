@Test public void should_throw_error_if_array_is_null(){
  thrown.expect(NullPointerException.class);
  Arrays.hasOnlyNullElements(null);
}
