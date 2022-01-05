private static <T>void load(Class<T> targetClass){
  ServiceLoader.load(targetClass,ServiceLoaderTestCase.class.getClassLoader()).forEach(object -> LOGGER.trace("\t" + object.getClass().getName()));
}
