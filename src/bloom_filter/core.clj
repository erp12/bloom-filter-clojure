

(ns bloom-filter.core
  (:gen-class)
  (:require [ds-utils.hash-functions :as hash-funcs]
            [bloom-filter.params :as params]))


(defrecord BloomFilter [bit-array hash-family param-map])

(defn make-bloom-filter
  [num-items acceptable-false-positive-rate]
  (if (= acceptable-false-positive-rate 0)
    (throw (Exception. "acceptable-false-positive-rate cannot be 0.")))
  (let [p (params/optimal-bloom-filter-params num-items acceptable-false-positive-rate)
        bit-array (vec (repeat (:num-bits p) false))
        hash-family (hash-funcs/make-universal-hash-family (:num-hash-funcs p)
                                                           (:num-bits p))]
    (BloomFilter. bit-array hash-family p)))

(defn add-element-bloom-filter
  [bloom-filter element]
  (let [hash-inds (hash-funcs/pass-to-hash-family (:hash-family bloom-filter)
                                                  element)
        new-bit-array (loop [ba (:bit-array bloom-filter)
                             btf hash-inds]
                        (if (empty? btf)
                          ba
                          (recur (assoc ba (first btf) true)
                                 (rest btf))))]
    (assoc bloom-filter :bit-array new-bit-array)))

(defn add-elements-bloom-filter
  [bloom-filter elements]
  (loop [bfilter bloom-filter
         els elements]
    (if (empty? els)
      bfilter
      (recur (add-element-bloom-filter bfilter (first els))
             (rest els)))))

(defn check-element-bloom-filter
  [bloom-filter element]
  (let [hash-inds (hash-funcs/pass-to-hash-family (:hash-family bloom-filter)
                                                  element)
        bit-array-subset (vec
                           (map #(get (:bit-array bloom-filter) %)
                                hash-inds))]
    (= (count bit-array-subset)
       (count (filter true? bit-array-subset)))))



