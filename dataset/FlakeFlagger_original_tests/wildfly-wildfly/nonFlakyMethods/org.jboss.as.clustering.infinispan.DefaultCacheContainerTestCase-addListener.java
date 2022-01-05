@Test public void addListener(){
  Object listener=new Object();
  this.subject.addListener(listener);
  verify(this.manager).addListener(listener);
}
