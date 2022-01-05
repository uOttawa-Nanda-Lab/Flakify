/** 
 * Test whether FixedWindowRollingPolicy throws an exception when the ActiveFileName is not set.
 */
@Test(expected=IllegalStateException.class) public void activeFileNameNotSet(){
  sizeBasedTriggeringPolicy.setMaxFileSize("100");
  sizeBasedTriggeringPolicy.start();
  fwrp.setFileNamePattern(CoreTestConstants.OUTPUT_DIR_PREFIX + "sizeBased-test1.%i");
  fwrp.start();
}
