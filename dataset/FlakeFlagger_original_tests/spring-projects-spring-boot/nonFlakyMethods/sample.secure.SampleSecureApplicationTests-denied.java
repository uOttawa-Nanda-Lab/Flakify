@Test(expected=AccessDeniedException.class) public void denied() throws Exception {
  SecurityContextHolder.getContext().setAuthentication(this.authentication);
  assertEquals(this.service.denied(),"Goodbye World");
}
