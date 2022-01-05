@Test public void should_create_error_message_with_custom_comparison_strategy(){
  ErrorMessageFactory factory=shouldContainExactly(newArrayList("Yoda","Han"),newArrayList("Luke","Yoda"),newLinkedHashSet("Luke"),newLinkedHashSet("Han"),CASE_INSENSITIVE_COMPARISON_STRATEGY);
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertThat(message).isEqualTo("[Test] \n" + "Expecting:\n" + "  <[\"Yoda\", \"Han\"]>\n"+ "to contain exactly (and in same order):\n"+ "  <[\"Luke\", \"Yoda\"]>\n"+ "but some elements were not found:\n"+ "  <[\"Luke\"]>\n"+ "and others were not expected:\n"+ "  <[\"Han\"]>\n"+ "when comparing values using 'CaseInsensitiveStringComparator'");
}
