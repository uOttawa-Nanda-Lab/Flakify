@Test public void noOutputIfContextHasAStatusListener(){
  System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY,ClassicTestConstants.INPUT_PREFIX + "issue/logback292.xml");
  System.setProperty(ContextInitializer.STATUS_LISTENER_CLASS,NopStatusListener.class.getName());
  StaticLoggerBinderFriend.reset();
  assertEquals(0,tee.baos.size());
}
