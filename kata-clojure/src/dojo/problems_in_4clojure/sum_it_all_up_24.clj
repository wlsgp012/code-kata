(ns dojo.problems-in-4clojure.sum-it-all-up-24)
;; https://4clojure.oxal.org/#/problem/24

(def answer #(reduce + %))

(= (answer [1 2 3]) 6)
(= (answer (list 0 -2 5 5)) 8)
(= (answer #{4 2 1}) 7)
(= (answer '(0 0 -1)) -1)
(= (answer '(1 10 3)) 14)

;; others
(partial reduce +)
(partial apply +)
