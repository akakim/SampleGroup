package com.example.sslab.samplegroupapplication.util;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 ******************************************************************************
 * Taken from the JB source code, can be found in:
 * libcore/luni/src/main/java/libcore/io/DiskLruCache.java
 * or direct link:
 * https://android.googlesource.com/platform/libcore/+/android-4.1.1_r1/luni/src/main/java/libcore/io/DiskLruCache.java
 ******************************************************************************
 *
 * A cache that uses a bounded amount of space on a filesystem. Each cache
 * entry has a string key and a fixed number of values. Values are byte
 * sequences, accessible as streams or files. Each value must be between {@code
 * 0} and {@code Integer.MAX_VALUE} bytes in length.
 * 캐쉬는
 * <p>The cache stores its data in a directory on the filesystem. This
 * directory must be exclusive to the cache; the cache may delete or overwrite
 * files from its directory. It is an error for multiple processes to use the
 * same cache directory at the same time.
 * 여러 프로세스가 같은 캐쉬 디랙토리를 같은 시간에 참조하면 에러다.
 * <p>This cache limits the number of bytes that it will store on the
 * filesystem. When the number of stored bytes exceeds the limit, the cache will
 * remove entries in the background until the limit is satisfied. The limit is
 * not strict: the cache may temporarily exceed it while waiting for files to be
 * deleted. The limit does not include filesystem overhead or the cache
 * journal so space-sensitive applications should set a conservative limit.
 *
 * 이캐쉬이 한게는 바이트의 수를 제한한다.(파일 시스템상의
 * <p>Clients call {@link #edit} to create or update the values of an entry. An
 * entry may have only one editor at one time; if a value is not available to be
 * edited then {@link #edit} will return null.
 * <ul>
 *     <li>When an entry is being <strong>created</strong> it is necessary to
 *         supply a full set of values; the empty value should be used as a
 *         placeholder if necessary.
 *     <li>When an entry is being <strong>edited</strong>, it is not necessary
 *         to supply data for every value; values default to their previous
 *         value.
 * </ul>
 * Every {@link #edit} call must be matched by a call to {@link Editor#commit}
 * or {@link Editor#abort}. Committing is atomic: a read observes the full set
 * of values as they were before or after the commit, but never a mix of values.
 *
 * <p>Clients call {@link #get} to read a snapshot of an entry. The read will
 * observe the value at the time that {@link #get} was called. Updates and
 * removals after the call do not impact ongoing reads.
 *
 * <p>This class is tolerant of some I/O errors. If files are missing from the
 * filesystem, the corresponding entries will be dropped from the cache. If
 * an error occurs while writing a cache value, the edit will fail silently.
 * Callers should handle other problems by catching {@code IOException} and
 * responding appropriately.
 */

public class DiskLruCache implements Cloneable {
//    static final String JOURNAL_FILE = "journal";
//    static final String JOURNAL_FILE_TMP = "journal.tmp";
//    static final String MAGIC = "libcore.io.DiskLruCache";
//    static final String VERSION_1 = "1";
//    static final long ANY_SEQUENCE_NUMBER = -1;
//    private static final String CLEAN = "CLEAN";
//    private static final String DIRTY = "DIRTY";
//    private static final String REMOVE = "REMOVE";
//    private static final String READ = "READ";
//
//    private static final Charset UTF_8 = Charset.forName("UTF-8");
//    private static final int IO_BUFFER_SIZE = 8 * 1024;
//
//     /*
//     * This cache uses a journal file named "journal". A typical journal file
//     * looks like this:
//     *     libcore.io.DiskLruCache
//     *     1
//     *     100
//     *     2
//     *
//     *     CLEAN 3400330d1dfc7f3f7f4b8d4d803dfcf6 832 21054
//     *     DIRTY 335c4c6028171cfddfbaae1a9c313c52
//     *     CLEAN 335c4c6028171cfddfbaae1a9c313c52 3934 2342
//     *     REMOVE 335c4c6028171cfddfbaae1a9c313c52
//     *     DIRTY 1ab96a171faeeee38496d8b330771a7a
//     *     CLEAN 1ab96a171faeeee38496d8b330771a7a 1600 234
//     *     READ 335c4c6028171cfddfbaae1a9c313c52
//     *     READ 3400330d1dfc7f3f7f4b8d4d803dfcf6
//     *
//     * The first five lines of the journal form its header.
//     * 첫번째 줄은 journal form의 해더이다.
//     * They are the constant string "libcore.io.DiskLruCache", the disk cache's version,
//     * the application's version, the value count, and a blank line.
//     *
//     * Each of the subsequent lines in the file is a record of the state of a
//     * cache entry.
//     *
//     * Each line contains space-separated values: a state, a key,
//     * and optional state-specific values.
//     *   o DIRTY lines track that an entry is actively being created or updated.
//     *     Every successful DIRTY action should be followed by a CLEAN or REMOVE
//     *     action. DIRTY lines without a matching CLEAN or REMOVE indicate that
//     *     temporary files may need to be deleted.
//     *   o CLEAN lines track a cache entry that has been successfully published
//     *     and may be read. A publish line is followed by the lengths of each of
//     *     its values.
//     *   o READ lines track accesses for LRU.
//     *   o REMOVE lines track entries that have been deleted.
//     *
//     * The journal file is appended to as cache operations occur. The journal may
//     * occasionally be compacted by dropping redundant lines. A temporary file named
//     * "journal.tmp" will be used during compaction; that file should be deleted if
//     * it exists when the cache is opened.
//     */
//
//    private final File directory;
//    private final File journalFile;
//    private final File journalFileTmp;
//    private final int appVersion;
//    private final long maxSize;
//    private final int valueCount;
//    private long size = 0;
//    private Writer journalWriter;
//    private final LinkedHashMap<String, Entry> lruEntries
//            = new LinkedHashMap<String, Entry>(0, 0.75f, true);
//    private int redundantOpCount;
//
//    private DiskLruCache(File directory, int appVersion, int valueCount, long maxSize) {
//        this.directory = directory;
//        this.appVersion = appVersion;
//        this.journalFile = new File(directory, JOURNAL_FILE);
//        this.journalFileTmp = new File(directory, JOURNAL_FILE_TMP);
//        this.valueCount = valueCount;
//        this.maxSize = maxSize;
//    }
//
//    /**
//     * To differentiate between old and current snapshots, each entry is given
//     * a sequence number each time an edit is committed. A snapshot is stale if
//     * its sequence number is not equal to its entry's sequence number.
//     *
//     * 단편적으로 기능만 보자면 어떤 자료를 받아서 start 부터 end 까지의 복사 후
//     * 입력했던 component타입으로 반환한다.
//     */
//    private long nextSequenceNumber = 0;
//
//    /* From java.util.Arrays */
//    @SuppressWarnings("unchecked")
//    private static <T> T[] copyOfRange(T[] original, int start, int end) {
//        final int originalLength = original.length;// For exception priority compatibility.
//        if (start > end) {
//            throw new IllegalArgumentException();
//        }
//        if (start < 0 || start > originalLength) {
//            throw new ArrayIndexOutOfBoundsException();
//        }
//        final int resultLength = end - start;
//        final int copyLength = Math.min(resultLength, originalLength - start);
//        final T[] result = (T[]) Array
//                .newInstance(original.getClass().getComponentType(),start,resultLength);
//        System.arraycopy(original,start,result,0,copyLength);
//        return result;
//
//    }
//
//    /**
//     * Returns the remainder of 'reader' as a string, closing it when done.
//     */
//    public static String readFully(Reader reader) throws IOException {
//        try{
//            StringWriter writer = new StringWriter();
//            char [] buffer = new char [1024];
//            int count;
//            while ((count = reader.read(buffer)) != -1){
//                writer.write(buffer,0,count);
//            }
//            return writer.toString();
//        }finally {
//            reader.close();
//        }
//    }
//
//    /**
//     * Returns the ASCII characters up to but not including the next "\r\n", or
//     * "\n".
//     *
//     * @throws java.io.EOFException if the stream is exhausted before the next newline
//     *     character.
//     */
//    //TODO : UTF_8을 이용할수 있도록 코딩한다.
//    public static String readAsciiLine(InputStream in) throws IOException {
//        // TODO: support UTF-8 here instead
//        StringBuilder result = new StringBuilder(80);
//        while(true){
//            int c = in.read();
//            if(c == -1){
//                throw  new EOFException();
//            }else if(c == '\n'){
//                break;
//            }
//            result.append((char) c);
//        }
//        int length = result.length();
//        if(length > 0 && result.charAt(length -1) =='\r'){
//            result.setLength(length -1);
//        }
//        return result.toString();
//    }
//
//    /**
//     * Closes 'closeable', ignoring any checked exceptions. Does nothing if 'closeable' is null.
//     */
//    public static void closeQuietly(Closeable closeable) {
//        if (closeable != null) {
//            try {
//                closeable.close();
//            } catch (RuntimeException rethrown) {
//                throw rethrown;
//            } catch (Exception ignored) {
//            }
//        }
//    }
//    /**
//     * Recursively delete everything in {@code dir}.
//     */
//    // TODO: this should specify paths as Strings rather than as Files
//    // 특정한 path의 문자열을 명시한다. 파일보단..
//    public static void deleteContents(File dir) throws IOException {
//        File[] files = dir.listFiles();
//        if (files == null) {
//            throw new IllegalArgumentException("not a directory: " + dir);
//        }
//        for (File file : files) {
//            if (file.isDirectory()) {
//                deleteContents(file);
//            }
//            // 파일 삭제
//            if (!file.delete()) {
//                throw new IOException("failed to delete file: " + file);
//            }
//        }
//    }
//
//    /** This cache uses a single background thread to evict entries. */
//    private final ExecutorService executorService = new ThreadPoolExecutor(0, 1,
//            60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
//
//    private final Callable<Void> cleanupCallable = new Callable<Void>() {
//        @Override
//        public Void call() throws Exception {
//            synchronized (DiskLruCache.this) {
//                if (journalWriter == null){
//                    return null;
//                    // closed
//                }
//                trimToSize();
//
//            }
//        }
//    }
//
//
//    /**
//     * Drops the entry for {@code key} if it exists and can be removed. Entries
//     * actively being edited cannot be removed.
//     *
//     * 1. 캐시가 닫혀있는지 확인
//     * 2. 유효한 킷값인지 확인
//     *
//     * @return true if an entry was removed.
//     */
//    public synchronized boolean remove(String key) throws IOException {
//       checkNotClosed();
//
//    }
//
//    /**
//     * Returns true if this cache has been closed.
//     */
//    public boolean isClosed() {
//        return journalWriter == null;
//    }
//
//
//
//    private void checkNotClosed(){
//        if(journalWriter == null){
//            throw new IllegalStateException("cache is closed");
//        }
//    }
//
//
//    public synchronized void close() throws IOException{
//        if(journalWriter == null){
//            return; // already closed;
//        }
//
//        for(Entry entry : new ArrayList<Entry>(lruEntries.values())){
//            if(entry.currentEditor != null){
//                entry.currentEditor
//            }
//        }
//    }
//    private void trimToSize() throws IOException {
//        while (size>maxSize){
//            final Map.Entry<String,Entry> toEvict = lruEntries.entrySet().iterator().next();
//            remove(toEvict.getKey());
//        }
//    }
//
//    public void delete() throws IOException{
//        close();
//
//    }
//    /**
//     *  To differentiate between old ad current snapshots, each entry is given
//     *  a sequence number each time and edit is committed. A snapshot is stale if
//     *  its sequence number is not equal to its entry's sequence number.
//     *
//     */
//
//    private static inputStreamToString(InputStream in) throws IOException{
//        return readFully(new InputStreamReader(in,UTF_8));
//    }
//    /**
//     * Edits the values for an entry.
//     */
//    public final class Editor {
//        private final Entry entry;
//        private boolean hasErrors;
//
//        private Editor(Entry entry) {
//            this.entry = entry;
//        }
//
//        /**
//         * Returns an unbuffered input stream to read the last committed value,
//         * or null if no value has been committed.
//         * 버퍼가 되지않은 상태의 입력 스트림을 반환한다. 왜냐하면 가장 최근 반영된 값을
//         * 읽거나 만약 null을 읽는다.
//         */
//        public InputStream newInputStream(int index) throws IOException {
//            synchronized (DiskLruCache.this) {
//                if (entry.currentEditor != this) {
//                    throw new IllegalStateException();
//                }
//                if (!entry.readable) {
//                    return null;
//                }
//                return new FileInputStream(entry.getCleanFile(index));
//            }
//        }
//        /**
//         * Returns the last committed value as a string, or null if no value
//         * has benn committed.
//         */
//        public String getString(int index) throws IOException{
//            InputStream in = newInputStream(index);
//            return in != null ? inputStreamToString(in): null;
//        }
//
//        /**
//         * Aborts this edit. This releases the edit lock so another edit may be
//         * started on the same key.
//         * 이 에디터를 abort한다. edit 이 잠겨있는걸 해제해서 다른 edit이
//         * 같은 키로 시작할 수 있도록 한다.
//         */
//        public void abort() throws IOException {
//            completeEdit(this, false);
//        }
//
//
//        private class FaultHidingOutputStream extends FilterOutputStream {
//
//        }
//
//    }
//
//
//
//    private final class Entry{
//        private final String key;
//
//        /** Lengths of this entry's files. */
//        private final long[] lengths;
//
//        /** True if this entry has ever been published */
//        private boolean readable;
//
//        /** The ongoing edit or null if this entry is not being edited. */
//        private Editor currentEditor;
//
//        /** The sequence number of the most recently committed edit to this entry. */
//        private long sequenceNumber;
//    }
}
