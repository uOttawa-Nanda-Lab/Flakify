@Test(expected=AuthenticationException.class) public void secure() throws Exception {
  assertEquals(this.service.secure(),"Hello Security");
}
