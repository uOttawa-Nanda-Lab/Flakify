@Test public void should_create_error_message(){
  assertEquals("[Test] \nsome elements are not mutually comparable in group:\n<[\"b\", 5, \"a\"]>",factory.create(new TestDescription("Test"),new StandardRepresentation()));
}
