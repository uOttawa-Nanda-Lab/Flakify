@Test public void sensibleMethodException() throws Exception {
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("Error processing condition on " + ErrorOnMethod.class.getName() + ".myBean");
  new AnnotationConfigApplicationContext(ErrorOnMethod.class);
}
