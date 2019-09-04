/*
 The MIT License (MIT)

 Copyright (c) 2015 Massimo Caliman

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */
package dev.caliman.use.clojure.from.java.examples;

import clojure.lang.Compiler;
import clojure.lang.PersistentVector;
import java.io.IOException;
import java.io.StringReader;
import clojure.lang.RT;
import clojure.lang.Var;

/**
 * @author Massimo Caliman
 */
public class UseClojure {

    public static void main(String[] args) throws IOException {

        new clojure.lang.RT();// needed since 1.5.0. This example use clojure-1.7.jar

        Compiler.load(new StringReader("(ns lab) (defn sum3 [a b c] (+ a b c))"));
        // get a reference to the function.
        Var var = RT.var("lab", "sum3");
        // call the function.
        Object result = var.invoke(1, 2, 3);
        System.out.println(result);

        Compiler.load(new StringReader("(ns lab) (defn tuple3 [a b c] [(+ a 1)(+ b 1)])"));
        var = RT.var("lab", "tuple3");
        Object r = var.invoke(1, 2, 3);
        if (r instanceof PersistentVector) {
            PersistentVector output = (PersistentVector) r;
            Long a = (Long) output.get(0);
            Long b = (Long) output.get(1);
            System.out.println("a=" + a);
            System.out.println("b=" + b);
        }
        System.out.println(r);
    }

}
