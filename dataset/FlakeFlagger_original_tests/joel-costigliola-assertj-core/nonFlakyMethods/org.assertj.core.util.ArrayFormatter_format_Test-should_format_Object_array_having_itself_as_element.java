@Test public void should_format_Object_array_having_itself_as_element(){
  Object[] array1={"Hello","World"};
  Object[] array2={array1};
  array1[1]=array2;
  assertEquals("[[\"Hello\", [...]]]",formatter.format(new StandardRepresentation(),array2));
}
