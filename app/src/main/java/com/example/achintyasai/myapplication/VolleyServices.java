package com.example.achintyasai.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Achintya Sai on 10/17/2016.
 */

public class VolleyServices {


        private static VolleyServices instance;
        private RequestQueue requestQueue;
        private ImageLoader imageLoader;

        private VolleyServices(Context context) {
            requestQueue = Volley.newRequestQueue(context);

            imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
                private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);

                @Override
                public Bitmap getBitmap(String url) {
                    return cache.get(url);
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {
                    cache.put(url,bitmap);
                }
            });
        }

        public static VolleyServices getInstance(Context context) {
            if (instance == null) {
                instance = new VolleyServices(context);
            }
            return instance;
        }

        public RequestQueue getRequestQueue() {
            return requestQueue;
        }
}
