@Test public void exceptionMessages() throws Exception {
  assertThat(new NoSuchCommandException("name").getMessage(),equalTo("'name' is not a valid command. See 'help'."));
}
