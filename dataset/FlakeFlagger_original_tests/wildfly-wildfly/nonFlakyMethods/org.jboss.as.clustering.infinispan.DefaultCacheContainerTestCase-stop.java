@Test public void stop(){
  this.subject.stop();
  verify(this.manager,never()).stop();
}
