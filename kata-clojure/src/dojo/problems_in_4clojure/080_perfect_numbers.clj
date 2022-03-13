(ns dojo.problems-in-4clojure.080-perfect-numbers)
;; https://4clojure.oxal.org/#/problem/80

(defn sol
  [n]
  (= n (reduce + (filter #(zero? (rem n %)) (range 1 n)))))


(= (sol 6) true)
(= (sol 7) false)
(= (sol 496) true)
(= (sol 500) false)
(= (sol 8128) true)
