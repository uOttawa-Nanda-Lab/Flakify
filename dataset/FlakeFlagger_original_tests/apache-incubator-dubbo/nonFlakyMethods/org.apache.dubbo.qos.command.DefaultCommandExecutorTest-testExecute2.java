@Test public void testExecute2() throws Exception {
  DefaultCommandExecutor executor=new DefaultCommandExecutor();
  String result=executor.execute(CommandContextFactory.newInstance("greeting",new String[]{"dubbo"},false));
  assertThat(result,equalTo("greeting dubbo"));
}
