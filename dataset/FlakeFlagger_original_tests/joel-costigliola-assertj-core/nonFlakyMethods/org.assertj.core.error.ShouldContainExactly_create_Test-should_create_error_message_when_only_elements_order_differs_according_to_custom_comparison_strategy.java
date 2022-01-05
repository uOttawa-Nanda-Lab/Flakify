@Test public void should_create_error_message_when_only_elements_order_differs_according_to_custom_comparison_strategy(){
  ErrorMessageFactory factory=elementsDifferAtIndex("Luke","Han",1,CASE_INSENSITIVE_COMPARISON_STRATEGY);
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertThat(message).isEqualTo("[Test] \n" + "Actual and expected have the same elements but not in the same order, at index 1 actual element was:\n" + "  <\"Luke\">\nwhereas expected element was:\n"+ "  <\"Han\">\n"+ "when comparing values using 'CaseInsensitiveStringComparator'");
}
