(ns dojo.problems-in-4clojure.009-conj-on-sets)
;; https://4clojure.oxal.org/#/problem/9

(= #{1 2 3 4} (conj #{1 4 3} 2))