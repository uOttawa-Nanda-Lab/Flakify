@Test(expected=NullPointerException.class) public void should_fail_if_start_is_null(){
  integers.assertIsStrictlyBetween(someInfo(),ONE,null,ONE);
}
