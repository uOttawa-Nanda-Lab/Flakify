@Test public void should_pass_if_actual_is_in_range(){
  bigDecimals.assertIsStrictlyBetween(someInfo(),ONE,ZERO,TEN);
}
