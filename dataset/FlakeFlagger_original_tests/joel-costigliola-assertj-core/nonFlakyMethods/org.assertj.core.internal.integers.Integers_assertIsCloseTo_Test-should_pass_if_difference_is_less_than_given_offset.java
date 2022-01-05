@Test public void should_pass_if_difference_is_less_than_given_offset(){
  integers.assertIsCloseTo(someInfo(),ONE,ONE,within(ONE));
  integers.assertIsCloseTo(someInfo(),ONE,TWO,within(TEN));
}
