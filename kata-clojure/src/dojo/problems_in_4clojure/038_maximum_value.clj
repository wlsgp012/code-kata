(ns dojo.problems-in-4clojure.038-maximum-value)
;; https://4clojure.oxal.org/#/problem/38

(defn answer
  [& col]
  (reduce #(if (> %1 %2) %1 %2) col))

(= (answer 1 8 3 4) 8)
(= (answer 30 20) 30)
(= (answer 45 67 11) 67)

;; others
(fn [& s] (last (sort s)))
