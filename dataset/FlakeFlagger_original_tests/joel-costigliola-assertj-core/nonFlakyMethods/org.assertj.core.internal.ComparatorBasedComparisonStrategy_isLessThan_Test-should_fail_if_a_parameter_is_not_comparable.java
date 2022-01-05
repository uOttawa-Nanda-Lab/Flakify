@Test public void should_fail_if_a_parameter_is_not_comparable(){
  thrown.expect(ClassCastException.class);
  caseInsensitiveComparisonStrategy.isLessThan(new Rectangle(),new Rectangle());
}
