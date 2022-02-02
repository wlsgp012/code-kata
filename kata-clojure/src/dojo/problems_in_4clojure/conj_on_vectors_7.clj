(ns dojo.problems-in-4clojure.conj-on-vectors-7)
;; https://4clojure.oxal.org/#/problem/7

(def answer [1 2 3 4])

(= answer (conj [1 2 3] 4))
(= answer (conj [1 2] 3 4))
