(ns dojo.problems-in-4clojure.072-rearranging-code-ii)
;; https://4clojure.oxal.org/#/problem/72

(def sol (partial apply +))

(= (sol (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))
   (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (sol))
   11)

;; others
(partial reduce +)
