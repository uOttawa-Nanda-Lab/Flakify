@Test public void sensibleClassException(){
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("Error processing condition on " + ErrorOnClass.class.getName());
  new AnnotationConfigApplicationContext(ErrorOnClass.class);
}
