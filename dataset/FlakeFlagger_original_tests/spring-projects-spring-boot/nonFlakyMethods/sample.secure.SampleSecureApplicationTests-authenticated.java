@Test public void authenticated() throws Exception {
  SecurityContextHolder.getContext().setAuthentication(this.authentication);
  assertEquals(this.service.secure(),"Hello Security");
}
