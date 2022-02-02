(ns dojo.problems-in-4clojure.conj-on-maps-10)
;; https://4clojure.oxal.org/#/problem/11

(= {:a 1, :b 2, :c 3} (conj {:a 1} [:b 2] [:c 3]))

(= {:a 1, :b 2, :c 3} (conj {:a 1} {:b 2} [:c 3]))
