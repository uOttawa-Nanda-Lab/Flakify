@Test public void should_create_error_message_with_custom_comparison_strategy(){
  factory=shouldNotBeEqual("Yoda","Luke",new ComparatorBasedComparisonStrategy(CaseInsensitiveStringComparator.instance));
  String message=factory.create(new TestDescription("Jedi"),new StandardRepresentation());
  assertEquals("[Jedi] \nExpecting:\n <\"Yoda\">\nnot to be equal to:\n <\"Luke\">\nwhen comparing values using 'CaseInsensitiveStringComparator'",message);
}
