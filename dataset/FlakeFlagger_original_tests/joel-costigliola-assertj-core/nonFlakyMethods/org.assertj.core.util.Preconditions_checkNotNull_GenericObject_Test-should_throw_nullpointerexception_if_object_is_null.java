@Test public void should_throw_nullpointerexception_if_object_is_null(){
  thrown.expect(NullPointerException.class);
  Object object=null;
  Preconditions.checkNotNull(object);
}
