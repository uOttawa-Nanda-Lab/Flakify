/*
Copyright (C) 2015 Electronic Arts Inc.  All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1.  Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
2.  Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
3.  Neither the name of Electronic Arts, Inc. ("EA") nor the names of
    its contributors may be used to endorse or promote products derived
    from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY ELECTRONIC ARTS AND ITS CONTRIBUTORS "AS IS" AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL ELECTRONIC ARTS OR ITS CONTRIBUTORS BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package com.ea.orbit.container.test;

import com.ea.orbit.annotation.Config;
import com.ea.orbit.concurrent.Task;
import com.ea.orbit.container.OrbitContainer;
import com.ea.orbit.container.Startable;

import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Singleton;

import java.util.Collections;

import static org.junit.Assert.*;

public class ContainerTest
{
    @Singleton
    public static class HelloWorld implements Startable
    {
        private boolean startCalled;
        private boolean stopCalled;

        public String sayHello()
        {
            return "hello";
        }

        @Override
        public Task start()
        {
            startCalled = true;
            return Task.done();
        }

        @Override
        public Task stop()
        {
            stopCalled = true;
            return Task.done();
        }
    }

    public static class HelloWorldNonSingleton
    {
        private boolean startCalled;
        private boolean stopCalled;

        public String sayHello()
        {
            return "hello";
        }
    }

    @Singleton
    public static class HelloHandler implements Startable
    {
        @Inject
        private HelloWorld helloWorld;

        @Config("hello.prefix")
        private String helloPrefix = "blah";

        public String sayHello()
        {
            return helloWorld.sayHello();
        }

        public String prefixedHello()
        {
            return helloPrefix + "." + helloWorld.sayHello();
        }

    }

    @Test public void noConfigurationTest(){OrbitContainer container=new OrbitContainer();container.add(HelloWorld.class);container.add(HelloHandler.class);container.start();final HelloHandler helloHandler=container.get(HelloHandler.class);assertEquals("blah.hello",helloHandler.prefixedHello());container.stop();}

}
