(ns dojo.problems-in-4clojure.nth-element-21)
;; https://4clojure.oxal.org/#/problem/21

(def answer #(first (drop %2 %1)))

(= (answer '(4 5 6 7) 2) 6)
(= (answer [:a :b :c] 0) :a)
(= (answer [1 2 3 4] 1) 2)
(= (answer '([1 2] [3 4] [5 6]) 2) [5 6])

;; other
(fn [s i]
  (if (zero? i)
    (first s)
    (recur (rest s) (dec i))))
