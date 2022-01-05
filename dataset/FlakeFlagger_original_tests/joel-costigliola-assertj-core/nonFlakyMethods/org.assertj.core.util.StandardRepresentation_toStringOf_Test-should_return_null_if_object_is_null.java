@Test public void should_return_null_if_object_is_null(){
  assertNull(new StandardRepresentation().toStringOf(null));
}
