@Test public void should_pass_if_difference_is_equal_to_given_offset(){
  integers.assertIsCloseTo(someInfo(),ONE,ONE,within(ZERO));
  integers.assertIsCloseTo(someInfo(),ONE,ZERO,within(ONE));
  integers.assertIsCloseTo(someInfo(),ONE,TWO,within(ONE));
}
