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
package ch.qos.logback.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import ch.qos.logback.core.spi.LifeCycle;

public class ContextBaseTest {

  private InstrumentedLifeCycleManager lifeCycleManager =
      new InstrumentedLifeCycleManager();
  
  private InstrumentedContextBase context = 
      new InstrumentedContextBase(lifeCycleManager);

  @Test
  public void renameTest() {
    context.setName("hello");
    try {
      context.setName("x");
      fail("renaming is not allowed");
    } catch (IllegalStateException ise) {
    }
  }

  private static class InstrumentedContextBase extends ContextBase {
  
    private final LifeCycleManager lifeCycleManager;
    
    public InstrumentedContextBase(LifeCycleManager lifeCycleManager) {
      this.lifeCycleManager = lifeCycleManager;
    }
  
    @Override
    protected LifeCycleManager getLifeCycleManager() {
      return lifeCycleManager;
    }
    
  }

  private static class InstrumentedLifeCycleManager extends LifeCycleManager {
    
    private LifeCycle lastComponent;
    private boolean reset;
    
    @Override
    public void register(LifeCycle component) {
      lastComponent = component;
      super.register(component);
    }
    
    @Override
    public void reset() {
      reset = true;
      super.reset();
    }

    public LifeCycle getLastComponent() {
      return lastComponent;
    }

    public boolean isReset() {
      return reset;
    }
    
  }

}
