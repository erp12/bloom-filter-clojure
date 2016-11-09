# Introduction to bloom-filter

Let's make a simple bloom filter to determine if an email address is part of a whitelist!

Let's start by `require`-ing the library.

```clj
(require '[bloom-filter.core :as bf])
```

Next we must create the BloomFilter object. Creating a bloom filter requires 2 peiced of information.

1. The number of items that you expect to store. We will use 10000.
2. The accpetable false positive rate. Lets use 1 out of 100, or 0.01

For this example, we will store our bloom filter in an atom.

```clj
(def my-bloom-filter (atom (bf/make-bloom-filter 10000 0.01)))
```

Next, we will need to generate a set of email addresses to serve as our whitelist. See the `test/bloom-filter/generate_date.clj` file.

The bloom filter must then be preprocessed. To preprocess the bloom filter we will pass our whitelist to the family of hash functions, and store the results. This is handled by the `add-elements-bloom-filter` function.

```clj
(swap! my-bloom-filter #(bf/add-elements-bloom-filter % email-address-whitelist))
```

To check if an item is part of our whitelist (with a 0.01 probability of being wrong), we will pass some email addresses to `check-element-bloom-filter`.

```clj
(def new-email-addresses ["alan.alexander@amail.com" "bettyBloom@bmail.net" "chris.Campbell@cmail.org" "danielDougal@dmail.edu"])
(map #(check-element-bloom-filter bfilter %) new-email-addresses)

;; Result will be a list of boolean values.

```

