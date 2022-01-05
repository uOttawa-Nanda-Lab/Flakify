@Test public void executeMultipleTimesDoesntThrowOutOfMemoryException(){
  final RubySassEngine engine=new RubySassEngine();
  for (int i=0; i < 100; i++) {
    engine.process("#navbar {width: 80%;}");
  }
}
