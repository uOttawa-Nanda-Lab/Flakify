@Test public void should_pass_if_difference_is_equal_to_given_offset(){
  longs.assertIsCloseTo(someInfo(),ONE,ONE,within(ZERO));
  longs.assertIsCloseTo(someInfo(),ONE,ZERO,within(ONE));
  longs.assertIsCloseTo(someInfo(),ONE,TWO,within(ONE));
}
