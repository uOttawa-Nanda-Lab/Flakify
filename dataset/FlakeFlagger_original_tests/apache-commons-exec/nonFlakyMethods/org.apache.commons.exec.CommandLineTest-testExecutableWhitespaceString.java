public void testExecutableWhitespaceString(){
  try {
    CommandLine cmdl=new CommandLine("   ");
    fail("Must throw IllegalArgumentException");
  }
 catch (  IllegalArgumentException e) {
  }
}
