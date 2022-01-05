@Test public void should_create_error_message_with_custom_comparison_strategy(){
  ErrorMessageFactory factory=shouldNotContain("Yoda","od",new ComparatorBasedComparisonStrategy(CaseInsensitiveStringComparator.instance));
  String message=factory.create(new TextDescription("Test"),new StandardRepresentation());
  assertEquals("[Test] \nExpecting:\n <\"Yoda\">\nnot to contain:\n <\"od\"> when comparing values using 'CaseInsensitiveStringComparator'",message);
}
