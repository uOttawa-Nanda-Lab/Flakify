@Test public void should_pass_if_difference_is_less_than_given_offset(){
  longs.assertIsCloseTo(someInfo(),ONE,ONE,within(ONE));
  longs.assertIsCloseTo(someInfo(),ONE,TWO,within(TEN));
}
