@Test(expected=RuntimeException.class) public void testGetLogger(){
  Logger failLogger=mock(Logger.class);
  FailsafeLogger failsafeLogger=new FailsafeLogger(failLogger);
  doThrow(new RuntimeException()).when(failLogger).error(anyString());
  failsafeLogger.getLogger().error("should get error");
}
