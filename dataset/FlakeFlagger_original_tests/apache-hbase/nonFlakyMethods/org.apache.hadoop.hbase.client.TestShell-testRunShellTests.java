@Test public void testRunShellTests() throws IOException {
  jruby.runScriptlet(PathType.ABSOLUTE,"src/test/ruby/tests_runner.rb");
}
