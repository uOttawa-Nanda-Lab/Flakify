@Test public void debugSwitchedOffForAppArgs(){
  CommandRunner runner=new CommandRunner("spring");
  runner.addCommand(new RunCommand());
  runner.runAndHandleErrors("run","samples/app.groovy","--","-d");
  assertFalse(this.output.toString().contains("Negative matches:"));
}
