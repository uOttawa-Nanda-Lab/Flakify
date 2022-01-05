@Test public void messageSentOnAdd(){
  this.observer.increment(new Delta<Integer>("foo",1));
  verify(this.channel).send(any(Message.class));
}
