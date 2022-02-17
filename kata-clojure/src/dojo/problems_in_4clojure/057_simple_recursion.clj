(ns dojo.problems-in-4clojure.057-simple-recursion)
;; https://4clojure.oxal.org/#/problem/57

(= '(5 4 3 2 1) ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5))

;; others
(range 5 0 -1)
