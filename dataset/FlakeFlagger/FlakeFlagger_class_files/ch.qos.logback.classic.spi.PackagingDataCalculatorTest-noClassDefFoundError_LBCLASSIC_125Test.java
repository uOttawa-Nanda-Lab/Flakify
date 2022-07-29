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
package ch.qos.logback.classic.spi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Test;

import ch.qos.logback.classic.util.TeztHelper;
import ch.qos.logback.core.util.SystemInfo;

public class PackagingDataCalculatorTest {

  double loop(int len, boolean withClassPackagingCalculation) {
    long start = System.nanoTime();
    for (int i = 0; i < len; i++) {
      doCalculateClassPackagingData(withClassPackagingCalculation);
    }
    return (1.0 * System.nanoTime() - start) / len / 1000;
  }

  private ClassLoader makeBogusClassLoader() throws MalformedURLException {
    ClassLoader currentClassLoader = this.getClass().getClassLoader();
    return new BogusClassLoader(new URL[] {},
        currentClassLoader);
  }

  @Test public void noClassDefFoundError_LBCLASSIC_125Test() throws MalformedURLException{ClassLoader cl=(URLClassLoader)makeBogusClassLoader();Thread.currentThread().setContextClassLoader(cl);Throwable t=new Throwable("x");ThrowableProxy tp=new ThrowableProxy(t);StackTraceElementProxy[] stepArray=tp.getStackTraceElementProxyArray();StackTraceElement bogusSTE=new StackTraceElement("com.Bogus","myMethod","myFile",12);stepArray[0]=new StackTraceElementProxy(bogusSTE);PackagingDataCalculator pdc=tp.getPackagingDataCalculator();pdc.calculate(tp);}

}
