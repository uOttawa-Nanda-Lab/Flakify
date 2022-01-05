@Test public void removeListener(){
  Object listener=new Object();
  this.subject.removeListener(listener);
  verify(this.manager).removeListener(listener);
}
