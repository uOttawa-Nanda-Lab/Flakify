@Test public void messageSentOnSet(){
  this.observer.set(new Metric<Double>("foo",1d));
  verify(this.channel).send(any(Message.class));
}
