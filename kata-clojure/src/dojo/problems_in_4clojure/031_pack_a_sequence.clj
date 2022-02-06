(ns dojo.problems-in-4clojure.031-pack-a-sequence)
;; https://4clojure.oxal.org/#/problem/31

(defn answer
  [xs]
  (partition-by identity xs))

(= (answer [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
(= (answer [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
(= (answer [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))

;; others
(partial partition-by str)
