(ns dojo.problems-in-4clojure.compress-seq)
;http://www.4clojure.com/problem/30

(defn sol [col]
  (reduce
    #(if (= (last %1) %2) %1 (conj %1 %2))
    []
    col))

(println (= (apply str (sol "Leeeeeerrroyyy")) "Leroy"))
(println (= (sol [1 1 2 3 3 2 2 3]) '(1 2 3 2 3)))
(println (= (sol [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2])))

;other solutions
#(map first (partition-by identity %))