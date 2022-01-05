@Test public void sourcesMustNotBeEmpty() throws Exception {
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("Sources must not be empty");
  new SpringApplication().run();
}
