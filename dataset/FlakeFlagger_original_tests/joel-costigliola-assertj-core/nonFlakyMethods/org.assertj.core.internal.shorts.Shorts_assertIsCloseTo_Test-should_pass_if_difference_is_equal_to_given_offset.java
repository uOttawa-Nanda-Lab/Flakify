@Test public void should_pass_if_difference_is_equal_to_given_offset(){
  shorts.assertIsCloseTo(someInfo(),ONE,ONE,within(ZERO));
  shorts.assertIsCloseTo(someInfo(),ONE,ZERO,within(ONE));
  shorts.assertIsCloseTo(someInfo(),ONE,TWO,within(ONE));
}
