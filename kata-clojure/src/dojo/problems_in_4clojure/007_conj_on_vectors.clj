(ns dojo.problems-in-4clojure.007-conj-on-vectors)
;; https://4clojure.oxal.org/#/problem/7

(def answer [1 2 3 4])

(= answer (conj [1 2 3] 4))
(= answer (conj [1 2] 3 4))