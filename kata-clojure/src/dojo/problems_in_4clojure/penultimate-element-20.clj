(ns dojo.problems-in-4clojure.penultimate-element-20)
;; https://4clojure.oxal.org/#/problem/20

(def answer (comp peek vec butlast))

(= (answer (list 1 2 3 4 5)) 4)
(= (answer ["a" "b" "c"]) "b")
(= (answer [[1 2] [3 4]]) [1 2])

;; other solution
#(-> % reverse second)

#(last (butlast %))

#(first (take-last 2 %))
