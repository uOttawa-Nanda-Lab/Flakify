@Test public void should_create_error_message_with_custom_comparison_strategy(){
  ErrorMessageFactory factory=shouldContainSequence(newArrayList("Yoda","Luke"),newArrayList("Han","Leia"),new ComparatorBasedComparisonStrategy(CaseInsensitiveStringComparator.instance));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <[\"Yoda\", \"Luke\"]>\nto contain sequence:\n <[\"Han\", \"Leia\"]>\n" + "when comparing values using 'CaseInsensitiveStringComparator'",message);
}
