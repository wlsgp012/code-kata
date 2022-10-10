(ns dojo.problems-in-4clojure.110-sequence-of-pronunciations)

(defn sol [v])

(= [[1 1] [2 1] [1 2 1 1]] (take 3 (sol [1])))

(= [3 1 2 4] (first (sol [1 1 1 4 4])))

(= [1 1 1 3 2 1 3 2 1 1] (nth (sol [1]) 6))

(= 338 (count (nth (sol [3 2]) 15)))
