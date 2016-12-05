package com.example.sslab.samplegroupapplication.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ala on 2016-11-27.
 */

public class CustomAsyncTask<Params,Progress,Reuslt>{
    private static final String LOG_TAG = "AsyncTask";

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 128;
    private static final int KEEP_ALIVE = 1;

//    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
//        private final AtomicInteger mCount = new AtomicInteger(1);
//
//        public Thread newThread(Runnable r) {
//            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
//        }
//    };
//
//
//    private static final BlockingQueue<Runnable> sPoolWorkQueue =
//            new LinkedBlockingQueue<Runnable>(10);
//
//    /**
//     * An {@link java.util.concurrent.Executor} that can be used to execute tasks in parallel.
//     */
//    public static final Executor THREAD_POOL_EXECUTOR
//            = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
//            TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory,
//            new ThreadPoolExecutor.DiscardOldestPolicy());
//
//    /**
//     * An {@link java.util.concurrent.Executor} that executes tasks one at a time in serial
//     * order.  This serialization is global to a particular process.
//     */
//    public static final Executor SERIAL_EXECUTOR = Executors.newSingleThreadExecutor(sThreadFactory);
//
//
//    private static abstract class WorkerRunnable<Params, Result> implements Callable<Result> {
//        Params[] mParams;
//    }
//
//    /**
//     * Override this method to perform a computation on a background thread. The
//     * specified parameters are the parameters passed to {@link #execute}
//     * by the caller of this task.
//     *
//     * This method can call {@link #publishProgress} to publish updates
//     * on the UI thread.
//     *
//     * @param params The parameters of the task.
//     *
//     * @return A result, defined by the subclass of this task.
//     *
//     * @see #onPreExecute()
//     * @see #onPostExecute
//     * @see #publishProgress
//     */
//    protected abstract Result doInBackground(Params... params);
//
//    /**
//     * Runs on the UI thread before {@link #doInBackground}.
//     *
//     * @see #onPostExecute
//     * @see #doInBackground
//     */
//    protected void onPreExecute() {
//    }
//
//    /**
//     * <p>Runs on the UI thread after {@link #doInBackground}. The
//     * specified result is the value returned by {@link #doInBackground}.</p>
//     *
//     * <p>This method won't be invoked if the task was cancelled.</p>
//     *
//     * @param result The result of the operation computed by {@link #doInBackground}.
//     *
//     * @see #onPreExecute
//     * @see #doInBackground
//     * @see #onCancelled(Object)
//     */
//    @SuppressWarnings({"UnusedDeclaration"})
//    protected void onPostExecute(Result result) {
//    }
//
//    /**
//     * Runs on the UI thread after {@link #publishProgress} is invoked.
//     * The specified values are the values passed to {@link #publishProgress}.
//     *
//     * @param values The values indicating progress.
//     *
//     * @see #publishProgress
//     * @see #doInBackground
//     */
//    @SuppressWarnings({"UnusedDeclaration"})
//    protected void onProgressUpdate(Progress... values) {
//    }
//
//    /**
//     * <p>Runs on the UI thread after {@link #cancel(boolean)} is invoked and
//     * {@link #doInBackground(Object[])} has finished.</p>
//     *
//     * <p>The default implementation simply invokes {@link #onCancelled()} and
//     * ignores the result. If you write your own implementation, do not call
//     * <code>super.onCancelled(result)</code>.</p>
//     *
//     * @param result The result, if any, computed in
//     *               {@link #doInBackground(Object[])}, can be null
//     *
//     * @see #cancel(boolean)
//     * @see #isCancelled()
//     */
//    @SuppressWarnings({"UnusedParameters"})
//    protected void onCancelled(Result result) {
//        onCancelled();
//    }
//
//    /**
//     * <p>Applications should preferably override {@link #onCancelled(Object)}.
//     * This method is invoked by the default implementation of
//     * {@link #onCancelled(Object)}.</p>
//     *
//     * <p>Runs on the UI thread after {@link #cancel(boolean)} is invoked and
//     * {@link #doInBackground(Object[])} has finished.</p>
//     *
//     * @see #onCancelled(Object)
//     * @see #cancel(boolean)
//     * @see #isCancelled()
//     */
//    protected void onCancelled() {
//    }
//
//
//    @SuppressWarnings({"RawUseOfParameterizedType"})
//    private static class CustomAsyncTaskResult<Data> {
//        final CustomAsyncTask mTask;
//        final Data[] mData;
//
//        CustomAsyncTaskResult(CustomAsyncTask task, Data... data) {
//            mTask = task;
//            mData = data;
//        }
//    }
}
