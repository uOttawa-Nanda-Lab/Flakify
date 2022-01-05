@Test public void should_return_true_if_objects_are_equal_according_to_comparison_strategy(){
  assertTrue(caseInsensitiveComparisonStrategy.areEqual("Yoda","Yoda"));
  assertTrue(caseInsensitiveComparisonStrategy.areEqual("Yoda","YODA"));
  assertTrue(caseInsensitiveComparisonStrategy.areEqual("YOda","YodA"));
}
