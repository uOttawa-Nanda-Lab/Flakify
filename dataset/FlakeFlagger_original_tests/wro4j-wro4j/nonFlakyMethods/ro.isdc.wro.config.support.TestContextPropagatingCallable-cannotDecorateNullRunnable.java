@Test(expected=NullPointerException.class) public void cannotDecorateNullRunnable(){
  ContextPropagatingCallable.decorate(null);
}
