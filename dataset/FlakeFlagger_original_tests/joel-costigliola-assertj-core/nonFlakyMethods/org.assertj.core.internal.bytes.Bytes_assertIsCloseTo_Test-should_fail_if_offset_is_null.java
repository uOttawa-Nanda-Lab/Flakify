@Test(expected=NullPointerException.class) public void should_fail_if_offset_is_null(){
  bytes.assertIsCloseTo(someInfo(),ONE,ZERO,null);
}
