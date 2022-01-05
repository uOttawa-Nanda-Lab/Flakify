@Test public void should_pass_if_difference_is_equal_to_given_offset(){
  bytes.assertIsCloseTo(someInfo(),ONE,ONE,within(ZERO));
  bytes.assertIsCloseTo(someInfo(),ONE,ZERO,within(ONE));
  bytes.assertIsCloseTo(someInfo(),ONE,TWO,within(ONE));
}
