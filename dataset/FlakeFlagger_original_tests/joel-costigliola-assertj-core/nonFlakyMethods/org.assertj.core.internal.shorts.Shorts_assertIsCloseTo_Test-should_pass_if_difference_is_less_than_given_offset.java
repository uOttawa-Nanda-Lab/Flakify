@Test public void should_pass_if_difference_is_less_than_given_offset(){
  shorts.assertIsCloseTo(someInfo(),ONE,ONE,within(ONE));
  shorts.assertIsCloseTo(someInfo(),ONE,TWO,within(TEN));
}
