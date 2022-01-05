@Test public void should_create_error_message_with_period_lower_boundary_included(){
  ErrorMessageFactory factory=shouldNotBeBetween(parse("2012-01-01"),parse("2011-01-01"),parse("2012-01-01"),true,false);
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <2012-01-01T00:00:00>\nnot to be in period:\n [2011-01-01T00:00:00, 2012-01-01T00:00:00[",message);
}
