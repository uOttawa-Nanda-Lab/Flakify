@Test(expected=NullPointerException.class) public void should_fail_if_expected_value_is_null(){
  doubles.assertIsCloseTo(someInfo(),ONE,null,within(ONE));
}
