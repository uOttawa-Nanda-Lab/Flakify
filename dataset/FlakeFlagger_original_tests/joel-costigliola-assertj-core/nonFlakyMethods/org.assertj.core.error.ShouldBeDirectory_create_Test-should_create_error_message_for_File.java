@Test public void should_create_error_message_for_File(){
  final File file=new FakeFile("xyz");
  factory=shouldBeDirectory(file);
  message=factory.create(description,representation);
  expectedMessage=String.format("[Test] " + FILE_SHOULD_BE_DIRECTORY,file);
  assertEquals(expectedMessage,message);
}
