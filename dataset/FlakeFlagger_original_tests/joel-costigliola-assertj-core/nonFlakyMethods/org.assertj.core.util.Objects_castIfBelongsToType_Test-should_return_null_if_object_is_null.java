@Test public void should_return_null_if_object_is_null(){
  assertNull(Objects.castIfBelongsToType(null,String.class));
}
