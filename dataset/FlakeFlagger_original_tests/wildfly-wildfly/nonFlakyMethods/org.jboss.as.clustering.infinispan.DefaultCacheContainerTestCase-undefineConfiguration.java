@Test public void undefineConfiguration(){
  this.subject.undefineConfiguration("test");
  verify(this.manager).undefineConfiguration("test");
}
