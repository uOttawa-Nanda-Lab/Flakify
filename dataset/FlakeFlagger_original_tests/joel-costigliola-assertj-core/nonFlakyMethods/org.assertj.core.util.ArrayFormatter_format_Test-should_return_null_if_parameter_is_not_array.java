@Test public void should_return_null_if_parameter_is_not_array(){
  assertNull(formatter.format(new StandardRepresentation(),"Hello"));
}
