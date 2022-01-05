@Test(timeout=20000) public void testLBCLASSIC_81() throws JoranException {
  getLoggerThread.start();
  for (int i=0; i < 500; i++) {
    ByteArrayInputStream baos=new ByteArrayInputStream("<configuration><root level=\"DEBUG\"/></configuration>".getBytes());
    jc.doConfigure(baos);
  }
}
