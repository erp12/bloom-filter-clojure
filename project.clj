
(defproject org.clojars.erp12/bloom-filter "0.4.1"
  :description "A simple implementation of bloom filters for Clojure"
  :url "https://github.com/erp12/bloom-filter-clojure"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [ds-utils "0.1.1"]]
  :plugins [[org.clojars.benfb/lein-gorilla "0.4.0"]]
  :target-path "target/%s"
  :repositories [["releases" {:url "https://clojars.org/repo"
                              :username :env
                              :sign-releases false
                              :password :env}]]
  :profiles {:uberjar {:aot :all}})

