

(ns bloom-filter.hash-functions
  (:gen-class)
  (:require [clojure.math.numeric-tower :as math]
            [com.hypirion.primes :as p]))


;; Generating has function values

(defn- generate-a
  [p]
  (rand-int p))

(defn- generate-b
  [p]
  (inc (rand-int (dec p))))

(defn- generate-prime
  [n]
  (p/first-above (* n (inc (rand-int 99)))))


;; Generateing hash function(s)

(defn make-hash-func
  [num-bits]
  (let [p (generate-prime num-bits)
        a (generate-a p)
        b (generate-b p)]
    (fn [item]
      (mod (mod (+ a
                   (* b (hash item)))
                p)
           num-bits))))

(defn make-hash-family
  [family-size num-bits]
  (vec (repeatedly family-size #(make-hash-func num-bits))))

(defn pass-to-hash-family
  [hash-family x]
  (vec (map #(% x) hash-family)))
