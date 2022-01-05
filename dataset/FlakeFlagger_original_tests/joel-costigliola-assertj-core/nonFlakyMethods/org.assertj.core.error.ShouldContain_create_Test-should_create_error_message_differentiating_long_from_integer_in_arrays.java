@Test public void should_create_error_message_differentiating_long_from_integer_in_arrays(){
  factory=shouldContain(newArrayList(5L,7L),newArrayList(5,7),newLinkedHashSet(5,7));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <[5L, 7L]>\nto contain:\n <[5, 7]>\nbut could not find:\n" + " <[5, 7]>\n",message);
}
