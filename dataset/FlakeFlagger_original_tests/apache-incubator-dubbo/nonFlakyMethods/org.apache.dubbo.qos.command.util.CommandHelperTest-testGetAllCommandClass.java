@Test public void testGetAllCommandClass() throws Exception {
  List<Class<?>> classes=CommandHelper.getAllCommandClass();
  assertThat(classes,containsInAnyOrder(GreetingCommand.class,Help.class,Ls.class,Offline.class,Online.class,Quit.class));
}
