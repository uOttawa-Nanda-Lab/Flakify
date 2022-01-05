@Test public void testTwoConcurrentCreationCalls(){
  testSuccessfulCreation();
  factory.create();
}
