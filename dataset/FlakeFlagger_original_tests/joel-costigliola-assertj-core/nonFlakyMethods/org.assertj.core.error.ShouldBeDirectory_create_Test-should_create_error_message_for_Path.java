@Test public void should_create_error_message_for_Path(){
  final Path path=mock(Path.class);
  factory=shouldBeDirectory(path);
  message=factory.create(description,representation);
  expectedMessage=String.format("[Test] " + PATH_SHOULD_BE_DIRECTORY,path);
  assertEquals(expectedMessage,message);
}
