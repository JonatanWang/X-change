package com.zyw.xchange;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Zhengyu Wang on 2016-11-14.
 * Email: zhengyuw@kth.se
 * To accept ArrayList as cache, i.e. bean
 * The bean send in is inherited from Serializable interfaces.
 * Use text stream as cache.
 */
public class DataInfoCache {

    /** Define cache name */
    private static String DataCache = "Data_Cache_File";

    /**
     * Save a group of data to the local file.
     * @param ctx  Context
     * @param data Seed
     * @param cacheName Cache File Name
     */
    public static<T> void saveListCache(Context ctx, ArrayList<T> data,String cacheName) {
        new DataCache<T>().saveGlobal(ctx, data, cacheName);
    }

    /**
     * Load by cache file name from the local file.
     * */
    public static<T> ArrayList<T> loadListCache(Context ctx,String cacheName) {
        return new DataCache<T>().loadGlobal(ctx,cacheName);
    }

    /**
     * Get a group of data
     * @param <T> Data cache: save or load
     */
    static class DataCache<T> {
        public void save(Context ctx, ArrayList<T> data, String name) {
            save(ctx, data, name, "");
        }

        public void saveGlobal(Context ctx, ArrayList<T> data, String name) {
            save(ctx, data, name, DataCache);
        }

        private void save(Context ctx, ArrayList<T> data, String name,String folder) {
            if (ctx == null) {
                return;
            }
            File file;
            if (!folder.isEmpty()) { // Control the fold & directory
                File fileDir = new File(ctx.getFilesDir(), folder);
                if (!fileDir.exists() || !fileDir.isDirectory()) {
                    fileDir.mkdir();
                }
                file = new File(fileDir, name);
            } else { // If the local file does not exist, create a new local file.
                file = new File(ctx.getFilesDir(), name);
            }
            if (file.exists()) { // Rewrite the local file
                file.delete();
            }
            Log.d("DataInfoCache", file.getAbsolutePath());
            try { // Write to the local file
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(data);
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public ArrayList<T> load(Context ctx, String name) {
            return load(ctx, name, "");
        }

        public ArrayList<T> loadGlobal(Context ctx, String name) {
            return load(ctx, name, DataCache);
        }

        private ArrayList<T> load(Context ctx, String name, String folder) {
            ArrayList<T> data = null;

            File file;
            if (!folder.isEmpty()) {
                File fileDir = new File(ctx.getFilesDir(), folder);
                if (!fileDir.exists() || !fileDir.isDirectory()) {
                    fileDir.mkdir();
                }
                file = new File(fileDir, name);
            } else {
                file = new File(ctx.getFilesDir(), name);
            }

            Log.d("DataInfoCache","File "+ file.getAbsolutePath());

            if (file.exists()) {
                try { // If the local file exists, load the file contents.
                    Log.d("DataInfoCache","Write object");
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                    data = (ArrayList<T>) ois.readObject();
                    ois.close();
                } catch (Exception e) {
                    Log.d("DataInfoCache", e.toString());
                }
            }
            /**If no data */
            if (data == null) {
                Log.d("DataInfoCache","Data == null");
                data = new ArrayList<T>();
            }
            return data;
        }
    }
}