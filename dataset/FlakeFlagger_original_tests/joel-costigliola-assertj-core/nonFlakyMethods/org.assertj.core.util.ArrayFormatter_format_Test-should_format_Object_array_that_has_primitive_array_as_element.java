@Test public void should_format_Object_array_that_has_primitive_array_as_element(){
  boolean booleans[]={true,false};
  Object[] array={"Hello",booleans};
  assertEquals("[\"Hello\", [true, false]]",formatter.format(new StandardRepresentation(),array));
}
