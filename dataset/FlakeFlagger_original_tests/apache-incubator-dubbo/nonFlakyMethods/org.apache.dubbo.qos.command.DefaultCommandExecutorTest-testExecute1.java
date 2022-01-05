@Test(expected=NoSuchCommandException.class) public void testExecute1() throws Exception {
  DefaultCommandExecutor executor=new DefaultCommandExecutor();
  executor.execute(CommandContextFactory.newInstance("not-exit"));
}
