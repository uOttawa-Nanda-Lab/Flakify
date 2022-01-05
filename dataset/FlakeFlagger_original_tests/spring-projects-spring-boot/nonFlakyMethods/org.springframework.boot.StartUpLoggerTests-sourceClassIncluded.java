@Test public void sourceClassIncluded(){
  new StartupInfoLogger(getClass()).logStarting(this.log);
  assertTrue("Wrong output: " + this.output,this.output.toString().contains("Starting " + getClass().getSimpleName()));
}
