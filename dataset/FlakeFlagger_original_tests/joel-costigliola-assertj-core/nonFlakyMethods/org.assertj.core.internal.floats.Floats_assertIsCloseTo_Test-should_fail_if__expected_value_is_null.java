@Test(expected=NullPointerException.class) public void should_fail_if__expected_value_is_null(){
  floats.assertIsCloseTo(someInfo(),ONE,null,within(ONE));
}
