@Test public void should_return_toString_of_array_of_Class(){
  Class<?>[] array={String.class,File.class};
  assertEquals("[java.lang.String, java.io.File]",new StandardRepresentation().toStringOf(array));
}
