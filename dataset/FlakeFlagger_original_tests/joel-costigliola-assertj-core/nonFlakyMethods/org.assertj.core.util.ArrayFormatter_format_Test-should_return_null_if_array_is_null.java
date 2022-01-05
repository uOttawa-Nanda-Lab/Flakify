@Test public void should_return_null_if_array_is_null(){
  assertNull(formatter.format(new StandardRepresentation(),null));
}
