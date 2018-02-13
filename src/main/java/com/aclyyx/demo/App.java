package com.aclyyx.demo;

import com.blade.Blade;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        Blade.me().listen(8080).start(App.class);
    }
}
