@Test public void throwIllegalStateWritingMissingPid() throws Exception {
  ApplicationPid pid=new ApplicationPid(null);
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("No PID available");
  pid.write(this.temporaryFolder.newFile());
}
