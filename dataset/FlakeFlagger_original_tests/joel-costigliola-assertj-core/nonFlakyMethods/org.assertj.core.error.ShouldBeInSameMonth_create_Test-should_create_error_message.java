@Test public void should_create_error_message(){
  ErrorMessageFactory factory=shouldBeInSameMonth(parse("2010-01-01"),parse("2010-02-01"));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <2010-01-01T00:00:00>\nto be on same year and month as:\n <2010-02-01T00:00:00>",message);
}
