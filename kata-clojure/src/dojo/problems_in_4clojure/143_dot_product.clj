(ns dojo.problems-in-4clojure.143-dot-product)

(def sol
  (fn [xs ys]
    (reduce + (map * xs ys))))

(= 0 (sol [0 1 0] [1 0 0]))

(= 3 (sol [1 1 1] [1 1 1]))

(= 32 (sol [1 2 3] [4 5 6]))

(= 256 (sol [2 5 6] [100 10 1]))

;; others
(comp
 (partial reduce +)
 (partial map *))

(fn [s1 s2] (apply + (map * s1 s2)))
