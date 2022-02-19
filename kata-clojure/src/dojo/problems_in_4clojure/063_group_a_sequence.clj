(ns dojo.problems-in-4clojure.063-group-a-sequence)
;; https://4clojure.oxal.org/#/problem/63

(defn sol
  [f s]
  (reduce (fn [m [k v]] (assoc m k (conj (m k []) v)))
          {}
          (map #(vector (f %) %) s)))


(= (sol #(> % 5) #{1 3 6 8}) {false [1 3], true [6 8]})
(= (sol #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
   {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})
(= (sol count [[1] [1 2] [3] [1 2 3] [2 3]])
   {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})

;; others
(fn [f coll]
  (reduce #(merge-with concat %1 {(f %2) [%2]}) {} coll))
