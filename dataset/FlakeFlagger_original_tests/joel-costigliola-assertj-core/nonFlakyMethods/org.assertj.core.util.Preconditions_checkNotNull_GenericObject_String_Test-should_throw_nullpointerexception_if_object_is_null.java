@Test public void should_throw_nullpointerexception_if_object_is_null(){
  thrown.expectNullPointerException(CUSTOM_MESSAGE);
  Object object=null;
  Preconditions.checkNotNull(object,CUSTOM_MESSAGE);
}
