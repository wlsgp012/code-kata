(ns dojo.problems-in-4clojure.011-conj-on-maps)
;; https://4clojure.oxal.org/#/problem/11

(= {:a 1, :b 2, :c 3} (conj {:a 1} [:b 2] [:c 3]))

(= {:a 1, :b 2, :c 3} (conj {:a 1} {:b 2} [:c 3]))