@Test public void start(){
  this.subject.start();
  verify(this.manager,never()).start();
}
