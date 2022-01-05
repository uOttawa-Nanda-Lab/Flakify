@Test(expected=NullPointerException.class) public void should_fail_if_start_is_null(){
  shorts.assertIsBetween(someInfo(),ONE,null,ONE);
}
