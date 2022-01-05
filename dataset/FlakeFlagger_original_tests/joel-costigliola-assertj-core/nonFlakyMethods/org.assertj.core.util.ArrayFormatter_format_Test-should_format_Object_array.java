@Test public void should_format_Object_array(){
  assertEquals("[\"Hello\", 'Anakin']",formatter.format(new StandardRepresentation(),new Object[]{"Hello",new Person("Anakin")}));
}
