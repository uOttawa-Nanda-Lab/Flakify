@Test public void should_throw_IllegalArgumentException_if_array_is_empty(){
  thrown.expectIllegalArgumentException(Preconditions.ARGUMENT_EMPTY);
  String[] array=new String[0];
  Preconditions.checkNotNullOrEmpty(array);
}
