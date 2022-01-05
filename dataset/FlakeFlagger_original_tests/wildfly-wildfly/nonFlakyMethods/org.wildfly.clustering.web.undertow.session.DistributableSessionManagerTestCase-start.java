@Test public void start(){
  this.adapter.start();
  verify(this.manager).start();
  verify(this.statistics).reset();
}
