@Test public void should_create_error_message_when_string_to_search_does_not_appear(){
  String message=factoryWithNoOccurence.create(new TestDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <\"motif\">\nto appear only once in:\n <\"aaamodifmoifaabbbmotfaaa\">\nbut it did not appear ",message);
}
