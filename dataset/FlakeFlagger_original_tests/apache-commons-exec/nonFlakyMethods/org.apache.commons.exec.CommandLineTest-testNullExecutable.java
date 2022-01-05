public void testNullExecutable(){
  try {
    CommandLine cmdl=new CommandLine((String)null);
    fail("Must throw IllegalArgumentException");
  }
 catch (  IllegalArgumentException e) {
  }
}
