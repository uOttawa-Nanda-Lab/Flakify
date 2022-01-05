@Test(expected=NullPointerException.class) public void should_fail_if_offset_is_null(){
  shorts.assertIsCloseTo(someInfo(),ONE,ZERO,null);
}
