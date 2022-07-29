/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2013, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package ch.qos.logback.classic;

import java.io.*;

import ch.qos.logback.core.util.CoreTestConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoggerSerializationTest {

  static final String SERIALIZATION_PREFIX = CoreTestConstants.TEST_INPUT_PREFIX+"/serialization/";

  // force SLF4J initialization for subsequent Logger readResolce ooperaiton
  org.slf4j.Logger unused = LoggerFactory.getLogger(this.getClass());
  LoggerContext lc;
  Logger logger;

  ByteArrayOutputStream bos;
  ObjectOutputStream oos;
  ObjectInputStream inputStream;

  @Before
  public void setUp() throws Exception {
    lc = new LoggerContext();
    lc.setName("testContext");
    logger = lc.getLogger(LoggerSerializationTest.class);
    // create the byte output stream
    bos = new ByteArrayOutputStream();
    oos = new ObjectOutputStream(bos);
  }

  @Test public void basicSerialization() throws IOException,ClassNotFoundException{Foo foo=new Foo(logger);foo.doFoo();Foo fooBack=writeAndRead(foo);fooBack.doFoo();}

  private Foo writeAndRead(Foo foo) throws IOException,
          ClassNotFoundException {
    writeObject(oos, foo);
    ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
    inputStream = new ObjectInputStream(bis);
    Foo fooBack = readFooObject(inputStream);
    inputStream.close();
    return fooBack;
  }

  Foo readFooObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
    return (Foo) readObject(inputStream);
  }
  private Object readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
    return inputStream.readObject();
  }

  private void writeObject(ObjectOutputStream oos, Object o) throws IOException {
    oos.writeObject(o);
    oos.flush();
    oos.close();
  }

}
