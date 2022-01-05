@Test(expected=NullPointerException.class) public void should_fail_if_offset_is_null(){
  integers.assertIsCloseTo(someInfo(),ONE,ZERO,null);
}
