@Test public void should_create_error_message_differentiating_double_from_float(){
  factory=shouldContain(newArrayList(5d,7d),newArrayList(5f,7f),newLinkedHashSet(5f,7f));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <[5.0, 7.0]>\nto contain:\n <[5.0f, 7.0f]>\nbut could not find:\n" + " <[5.0f, 7.0f]>\n",message);
}
