@Test public void preauth() throws Exception {
  SecurityContextHolder.getContext().setAuthentication(this.authentication);
  assertEquals(this.service.authorized(),"Hello World");
}
