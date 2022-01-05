@Test(expected=NullPointerException.class) public void should_fail_if_expected_value_is_null(){
  shorts.assertIsCloseTo(someInfo(),ONE,null,within(ONE));
}
