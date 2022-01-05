private static <T>void load(Class<T> targetClass){
  System.out.println(targetClass.getName() + ":");
  ServiceLoader.load(targetClass,ServiceLoaderTestCase.class.getClassLoader()).forEach(object -> System.out.println("\t" + object.getClass().getName()));
}
