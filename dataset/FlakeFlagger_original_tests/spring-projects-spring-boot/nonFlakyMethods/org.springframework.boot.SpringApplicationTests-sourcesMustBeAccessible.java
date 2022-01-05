@Test public void sourcesMustBeAccessible() throws Exception {
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("Cannot load configuration");
  new SpringApplication(InaccessibleConfiguration.class).run();
}
