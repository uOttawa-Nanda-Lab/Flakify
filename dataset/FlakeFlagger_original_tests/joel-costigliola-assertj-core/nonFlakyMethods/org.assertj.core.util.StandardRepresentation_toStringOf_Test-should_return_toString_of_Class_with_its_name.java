@Test public void should_return_toString_of_Class_with_its_name(){
  assertEquals("java.lang.Object",new StandardRepresentation().toStringOf(Object.class));
}
