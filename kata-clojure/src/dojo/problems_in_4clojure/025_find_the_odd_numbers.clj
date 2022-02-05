(ns dojo.problems-in-4clojure.025-find-the-odd-numbers)
;; https://4clojure.oxal.org/#/problem/25

(defn answer [l]
  (filter #(= 1 (rem % 2)) l))

(= (answer #{1 2 3 4 5}) '(1 3 5))
(= (answer [4 2 1 6]) '(1))
(= (answer [2 2 4 6]) '())
(= (answer [1 1 1 3]) '(1 1 1 3))


;; others
#(filter odd? %)
(partial filter odd?)