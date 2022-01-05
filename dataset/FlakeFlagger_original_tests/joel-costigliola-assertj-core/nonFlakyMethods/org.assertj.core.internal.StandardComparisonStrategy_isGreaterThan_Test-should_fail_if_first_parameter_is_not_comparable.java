@Test public void should_fail_if_first_parameter_is_not_comparable(){
  thrown.expect(IllegalArgumentException.class);
  standardComparisonStrategy.isGreaterThan(new Rectangle(),"foo");
}
