/*
* JBoss, Home of Professional Open Source
* Copyright 2005, JBoss Inc., and individual contributors as indicated
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/
package org.jboss.as.ejb3.concurrency;

import org.jboss.as.ejb3.component.singleton.EJBReadWriteLock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.ConcurrentAccessTimeoutException;
import javax.ejb.IllegalLoopbackException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Tests the {@link EJBReadWriteLock}
 *
 * @author Jaikiran Pai
 * @version $Revision: $
 */
public class EJBReadWriteLockTest {

    /**
     * Used in tests
     */
    private EJBReadWriteLock ejbReadWriteLock;

    @Before
    public void beforeTest() {
        this.ejbReadWriteLock = new EJBReadWriteLock();

    }

    /**
	 * Tests that a thread can first get a write lock and at a later point in time, get a read lock
	 * @throws Exception
	 */@Test public void testSameThreadCanGetWriteThenReadLock() throws Exception{Lock writeLock=this.ejbReadWriteLock.writeLock();writeLock.lock();Lock readLock=this.ejbReadWriteLock.readLock();try {boolean readLockAcquired=readLock.tryLock(2,TimeUnit.SECONDS);if (readLockAcquired){readLock.unlock();}Assert.assertTrue("Could not obtain read lock when write lock was held by the same thread!",readLockAcquired);}  finally {writeLock.unlock();}}

    /**
     * An implementation of {@link Runnable} which in its {@link #run()} method
     * will first obtain a lock and then will go to sleep for the specified amount
     * of time. After processing, it will unlock the {@link java.util.concurrent.locks.Lock}
     *
     * @author Jaikiran Pai
     * @version $Revision: $
     */
    private class ThreadHoldingWriteLock implements Runnable {
        /**
         * Lock
         */
        private Lock lock;

        /**
         * The amount of time, in milliseconds, this {@link ThreadHoldingWriteLock}
         * will sleep for in its {@link #run()} method
         */
        private long processingTime;

        /**
         * A latch for notifying any waiting threads
         */
        private CountDownLatch latch;

        /**
         * Creates a {@link ThreadHoldingWriteLock}
         *
         * @param latch          A latch for notifying any waiting threads
         * @param lock           A lock that will be used for obtaining a lock during processing
         * @param processingTime The amount of time in milliseconds, this thread will sleep (a.k.a process)
         *                       in its {@link #run()} method
         */
        public ThreadHoldingWriteLock(CountDownLatch latch, Lock lock, long processingTime) {
            this.lock = lock;
            this.processingTime = processingTime;
            this.latch = latch;
        }

        /**
         * Obtains a lock, sleeps for {@link #processingTime} milliseconds and then unlocks the lock
         *
         * @see Runnable#run()
         */
        @Override
        public void run() {
            // lock it!
            this.lock.lock();
            // process(sleep) for the specified time
            try {
                Thread.sleep(this.processingTime);
            } catch (InterruptedException e) {
                // ignore
            } finally {
                // unlock
                this.lock.unlock();
                // let any waiting threads know that we are done processing
                this.latch.countDown();
            }
        }
    }


}
