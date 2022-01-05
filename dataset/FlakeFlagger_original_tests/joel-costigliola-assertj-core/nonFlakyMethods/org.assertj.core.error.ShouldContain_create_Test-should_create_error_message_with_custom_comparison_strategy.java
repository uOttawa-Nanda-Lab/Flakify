@Test public void should_create_error_message_with_custom_comparison_strategy(){
  factory=shouldContain(newArrayList("Yoda"),newArrayList("Luke","Yoda"),newLinkedHashSet("Luke"),new ComparatorBasedComparisonStrategy(CaseInsensitiveStringComparator.instance));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <[\"Yoda\"]>\nto contain:\n <[\"Luke\", \"Yoda\"]>\nbut could not find:\n" + " <[\"Luke\"]>\nwhen comparing values using 'CaseInsensitiveStringComparator'",message);
}
