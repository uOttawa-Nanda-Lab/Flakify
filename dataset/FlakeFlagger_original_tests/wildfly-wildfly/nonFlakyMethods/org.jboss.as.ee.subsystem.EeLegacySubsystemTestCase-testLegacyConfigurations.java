@Test public void testLegacyConfigurations() throws Exception {
  final Pattern pattern=Pattern.compile("(subsystem)_\\d+_\\d+\\.xml");
  final String cp=WildFlySecurityManager.getPropertyPrivileged("java.class.path",".");
  final String[] entries=cp.split(Pattern.quote(File.pathSeparator));
  final List<String> configs=new ArrayList<>();
  for (  String entry : entries) {
    final Path path=Paths.get(entry);
    if (Files.isDirectory(path)) {
      Files.walkFileTree(path,new SimpleFileVisitor<Path>(){
        @Override public FileVisitResult visitFile(        final Path file,        final BasicFileAttributes attrs) throws IOException {
          final String name=file.getFileName().toString();
          if (pattern.matcher(name).matches()) {
            configs.add(name);
          }
          return FileVisitResult.CONTINUE;
        }
      }
);
    }
  }
  Assert.assertFalse("No configs were found",configs.isEmpty());
  for (  String configId : configs) {
    standardSubsystemTest(configId,false);
  }
}
