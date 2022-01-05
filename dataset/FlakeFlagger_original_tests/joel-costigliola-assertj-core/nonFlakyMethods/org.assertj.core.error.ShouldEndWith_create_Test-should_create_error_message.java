@Test public void should_create_error_message(){
  factory=shouldEndWith(newArrayList("Yoda","Luke"),newArrayList("Han","Leia"));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <[\"Yoda\", \"Luke\"]>\nto end with:\n <[\"Han\", \"Leia\"]>\n",message);
}
