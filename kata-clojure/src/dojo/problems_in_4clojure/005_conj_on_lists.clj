(ns dojo.problems-in-4clojure.005-conj-on-lists)
;; https://4clojure.oxal.org/#/problem/5


(def answer '(1 2 3 4))

(= answer (conj '(2 3 4) 1))

(= answer (conj '(3 4) 2 1))