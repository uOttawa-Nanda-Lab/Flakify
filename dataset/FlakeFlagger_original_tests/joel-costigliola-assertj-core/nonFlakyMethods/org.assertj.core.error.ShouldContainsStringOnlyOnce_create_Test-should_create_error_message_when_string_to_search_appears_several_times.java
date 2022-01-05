@Test public void should_create_error_message_when_string_to_search_appears_several_times(){
  String message=factoryWithSeveralOccurences.create(new TestDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <\"motif\">\nto appear only once in:\n <\"aaamotifmotifaabbbmotifaaa\">\nbut it appeared 3 times ",message);
}
